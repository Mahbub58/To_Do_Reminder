package to_do_reminder.EspritSoft.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.ViewPagerAdaptor;
import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.AditionalSystem.CheckTabeActivity;
import to_do_reminder.EspritSoft.AditionalSystem.RefreshActivity;
import to_do_reminder.EspritSoft.Notification.NotificationService;
import to_do_reminder.EspritSoft.Notification.NotificationServicePrivate;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.Tab.Tab1;
import to_do_reminder.EspritSoft.Tab.Tab2;
import to_do_reminder.EspritSoft.Tab.Tab3;
import to_do_reminder.EspritSoft.Tab.Tab4;
import to_do_reminder.EspritSoft.Tab.Tab5;
import to_do_reminder.EspritSoft.Tab.Tab6;
import to_do_reminder.EspritSoft.Tab.Tab7;
import to_do_reminder.EspritSoft.Tab.Tab8;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.service.CheckTotalTask;
import to_do_reminder.EspritSoft.service.Task_scheduler;
import to_do_reminder.EspritSoft.service.manageData;


public class Home extends AppCompatActivity {
    Toolbar toolbar;
    static ViewPager viewPager;
    TabLayout tabLayout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public static EditText Search;

    int loadList = 0;

    public static DBManager dbManager;
    public static String RecordID;
    saveData SaveData;
    public static Context context;
    public static List<customItem> mDatalist;
    public static ArrayList<String> TaskList;
    static FloatingActionButton fta;
    private ExpandableLayout expandableLayout;
    private ImageView expandButton;
    public static int loadpermision = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        dbManager = new DBManager(context);
        SaveData = new saveData(this);


        toolbar();

        init();


        //   expandLayoutSearch();

        mDatalist = new ArrayList<>();


        //tasklistName
        TaskList = new ArrayList<String>();
        TaskList.add("Default \t  ");
        TaskList.add("Private");
        loadList = saveData.LoadST();
        if (loadList == 1) {
            LoadTaskListName();
        } else {
            viewpagerTabeLayout();
        }


        //save task when first open app
        if (saveData.TasklistLoad() == 0) {
            saveArray();
            saveData.TasKListsav(1);
        }
        //Show task when notification is panding
        showNotifyAlart();

       // if(Build.VERSION.SDK_INT >=23){
//            Intent setr=new Intent(this, Task_scheduler.class);
//            startService(setr);
      //  }
        Intent setr=new Intent(context, CheckTotalTask.class);
        startService(setr);



        //rate App
        checkBatteryOptimization();
        if(saveData.RateLoad()==saveData.RateLoadAgain()){
            RateApp();
            int f=saveData.RateLoadAgain()+7;
            saveData.RateSaveAgain(f);
        }
        int r=saveData.RateLoad()+1;
        saveData.RateSave(r);


    }
    @SuppressLint("NewApi")
    private void checkBatteryOptimization() {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent();
            String packageName = context.getPackageName();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm.isIgnoringBatteryOptimizations(packageName)) {
                //     intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
               //      startActivity(intent);
            }else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }

        }
    }
//    boolean dialogActive=false;
    void showNotifyAlart() {
        if (saveData.LoadNotificationpandingData() == 1) {
            viewTask();
//            dialogActive=true;
        } else if (saveData.LoadNotificationpandingDataprivate() == 11) {
            passwordCheck();
        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(saveData.LoadNotificationpandingData()!=0)
//                  showNotifyAlart();
//            }
//        },200);
    }

    //save and load tabName list array
    public void saveArray() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Home.TaskList);
        editor.putString("task", json);
        editor.apply();
    }

    int newTab = 0;static int newTablist=0;

    void init() {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);

        fta = findViewById(R.id.fta);
        fta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewTask.class);
                intent.putExtra("check", 0);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        newTab = intent.getIntExtra("check", 0);
        if (newTab == 1) {
            progressListcreate();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    newTab = 0;
                    finish();
                }
            }, 3000);
        } else {
            newTab = 0;
        }


    }

    // floating bton visible and invisible
    @SuppressLint("RestrictedApi")
    public static void invisible() {
        fta.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    public static void visible() {
        fta.setVisibility(View.VISIBLE);

    }

    //=================REfresh Activity ============================
//    public static void RefreshActivity() {
//        RefreshActivity.finishActivity((AppCompatActivity) context);
////      //  RefreshActivity.recreatActivity((AppCompatActivity)context);
//          Intent intent = new Intent(context, Home.class);
//         context.startActivity(intent);
//      //  RefreshActivity.reActive((AppCompatActivity)context);
//    }


    //============= Tollbar ==============================================
    void toolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView hname = findViewById(R.id.hname);
        hname.setText("Task List");

        drawerLayout = findViewById(R.id.drawerLayout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.navigation);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.taskList);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.taskList:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.taskFnished:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), TaskFinished.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slid_in_right, R.anim.slid_in_left_out);
                                finish();
                            }
                        }, 200);

                        break;
                    case R.id.taskcencle:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent4 = new Intent(getApplicationContext(), CancledTask.class);
                                startActivity(intent4);
                                overridePendingTransition(R.anim.slid_in_right, R.anim.slid_in_left_out);
                                finish();
                            }
                        }, 200);
                        break;
                    case R.id.delete:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent2 = new Intent(getApplicationContext(), DeletedTask.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slid_in_right, R.anim.slid_in_left_out);
                                finish();
                            }
                        }, 200);

                        break;
                    case R.id.setting:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1 = new Intent(getApplicationContext(), setings.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.slid_in_botom, R.anim.defuly);
                            }
                        }, 200);
                        break;
                    case R.id.About:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent3 = new Intent(getApplicationContext(), About.class);
                                startActivity(intent3);
                                overridePendingTransition(R.anim.slid_in_botom, R.anim.defuly);
                            }
                        }, 200);
                        break;

                }

                return false;
            }
        });

    }


    //toolbarMenu
    public static String sText = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tolbar_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.searchbar);
        final MenuItem refresh = menu.findItem(R.id.Refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RefreshTab();
                return false;
            }
        });
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sText = newText;

                if (CheckTabeActivity.tab1activityNotNull == true) {
                    Tab1.LoadElement();
                }
                if (CheckTabeActivity.tab2activityNotNull == true) {
                    Tab2.LoadElement();
                }
                if (CheckTabeActivity.tab3activityNotNull == true) {
                    Tab3.LoadElement();
                }
                if (CheckTabeActivity.tab4activityNotNull == true) {
                    Tab4.LoadElement();
                }
                if (CheckTabeActivity.tab5activityNotNull == true) {
                    Tab5.LoadElement();
                }
                if (CheckTabeActivity.tab6activityNotNull == true) {
                    Tab6.LoadElement();
                }
                if (CheckTabeActivity.tab7activityNotNull == true) {
                    Tab7.LoadElement();
                }
                if (CheckTabeActivity.tab8activityNotNull == true) {
                    Tab8.LoadElement();
                }


                return false;
            }
        });

        return true;
    }

    //================= refresh all tab
    void RefreshTab() {
        sText = "";
        if (CheckTabeActivity.tab1activityNotNull == true) {
            Tab1.LoadElement();
        }
        if (CheckTabeActivity.tab2activityNotNull == true) {
            Tab2.LoadElement();
        }
        if (CheckTabeActivity.tab3activityNotNull == true) {
            Tab3.LoadElement();
        }
        if (CheckTabeActivity.tab4activityNotNull == true) {
            Tab4.LoadElement();
        }
        if (CheckTabeActivity.tab5activityNotNull == true) {
            Tab5.LoadElement();
        }
        if (CheckTabeActivity.tab6activityNotNull == true) {
            Tab6.LoadElement();
        }
        if (CheckTabeActivity.tab7activityNotNull == true) {
            Tab7.LoadElement();
        }
        if (CheckTabeActivity.tab8activityNotNull == true) {
            Tab8.LoadElement();
        }
    }


    //=================== FucasChange ============================================================================

    int ac = 0, ae = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (loadList == 1) {
            LoadTaskListName();
        }
        //  LoadAllData();

        if (loadpermision == 1 || loadpermision == 2) {
            viewpagerTabeLayout();
            loadpermision = 0;
        }
        //notify alart
        ac++;
        if (ac % 2 != 0 && ae == 0) {
            showNotifyAlart();
        }
        if (newTab == 1) {
            progressListcreate();
        }
        if(newTablist==1){
            newTablist=0;
            finish();
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);
        }
        super.onWindowFocusChanged(hasFocus);
    }

    void viewpagerTabeLayout() {


        ViewPagerAdaptor adaptor = new ViewPagerAdaptor(getSupportFragmentManager());
        adaptor.addFragment(new Tab1(), "All Task \t");
        for (int i = 0; i < TaskList.size(); i++) {
            switch (i) {
                case 0:
                    adaptor.addFragment(new Tab2(), TaskList.get(0));
                    break;
                case 1:
                    adaptor.addFragment(new Tab3(), TaskList.get(1));
                    break;
                case 2:
                    adaptor.addFragment(new Tab4(), TaskList.get(2));
                    break;
                case 3:
                    adaptor.addFragment(new Tab5(), TaskList.get(3));
                    break;
                case 4:
                    adaptor.addFragment(new Tab6(), TaskList.get(4));
                    break;
                case 5:
                    adaptor.addFragment(new Tab7(), TaskList.get(5));
                    break;
                case 6:
                    adaptor.addFragment(new Tab8(), TaskList.get(6));
                    break;
            }
        }

        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);


    }

    //===================== Load TaskListName ===============
    public void LoadTaskListName() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        TaskList = gson.fromJson(json, type);
        //load tab
        viewpagerTabeLayout();
        loadList = 0;
    }

    //======================= Backprease ===============
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        CheckTabeActivity.HomeActive=false;
        super.finish();
    }

    //================ dialog password Check
    //password check alartDialog
    public void passwordCheck() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
        View mView = getLayoutInflater().inflate(R.layout.view_task_check_pass, null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        final EditText mEmail = (EditText) mView.findViewById(R.id.pass);
        Button mLogin = (Button) mView.findViewById(R.id.chckPassword);
        TextView remainde = mView.findViewById(R.id.remaindagain);
        remainde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ae = 1;
                dialog.hide();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showNotifyAlart();
                        ae = 0;
                    }
                }, 10000);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveData.LoadPrivateTab().equals(mEmail.getText().toString())) {
                    viewTaskPrivate();
                    dialog.hide();
                } else {
                    mEmail.setText("");
                    mEmail.setHint("Wrong password");
                }


            }
        });


    }

    //=View Task in daialog
    public void viewTask() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
        View mViewt = getLayoutInflater().inflate(R.layout.view_task_dialog, null);
        mBuilder.setView(mViewt);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();



        TextView title = mViewt.findViewById(R.id.etitle);
        title.setText(saveData.LoadTitleNotifyDuplicat());
        TextView desc = mViewt.findViewById(R.id.edesc);
        desc.setText(saveData.LoadDescNotifyDuplicate());

        final Button cancel = (Button) mViewt.findViewById(R.id.ecencle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if (saveData.checkduplicatTaskprivateLoad() == 0) {
                if (saveData.LoadNotificationpandingData() == 1) {
                    String RecordIDLoad = String.valueOf(saveData.checkduplicatidLoad());

                    saveData.SaveNotificationpandingData(0);
                    ContentValues values = new ContentValues();
                    values.put(DBManager.ColID, RecordIDLoad);
                    values.put(DBManager.finished, "canceled");
                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
                    dbManager.Update(values, "id=?", SelectionArgsf);
                    if (NotificationService.notificationISactive == true) {
                        Intent serviceIntent = new Intent(context, NotificationService.class);
                        context.stopService(serviceIntent);
                    }
                }
//                } else {
//                    String RecordIDLoad = String.valueOf(saveData.checkduplicatidLoad());
//
//                    saveData.SaveNotificationpandingDataprivate(0);
//                    ContentValues values = new ContentValues();
//                    values.put(DBManager.ColID, RecordIDLoad);
//                    values.put(DBManager.finished, "canceledPrivate");
//                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
//                    dbManager.Update(values, "id=?", SelectionArgsf);
//                    if (NotificationServicePrivate.nptificationPrivateISactive == true) {
//                        Intent serviceIntent = new Intent(context, NotificationServicePrivate.class);
//                        context.stopService(serviceIntent);
//                    }
//                }
                RefreshTab();
                dialog.hide();
            }
        });
        Button complete = mViewt.findViewById(R.id.ecomplete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if (saveData.checkduplicatTaskprivateLoad() == 0) {
                if (saveData.LoadNotificationpandingData() == 1) {
                    String RecordIDLoad = String.valueOf(saveData.checkduplicatidLoad());
                    saveData.SaveNotificationpandingData(0);
                    ContentValues values = new ContentValues();
                    values.put(DBManager.ColID, RecordIDLoad);
                    values.put(DBManager.finished, "complete");
                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
                    dbManager.Update(values, "id=?", SelectionArgsf);
                    if (NotificationService.notificationISactive == true) {
                        Intent serviceIntent = new Intent(context, NotificationService.class);
                        context.stopService(serviceIntent);
                    }
                }
//                } else {
//                    String RecordIDLoad = String.valueOf(saveData.checkidLoad());
//                    saveData.checkidSave(Integer.parseInt(RecordIDLoad));
//                    saveData.SaveNotificationpandingDataprivate(0);
//                    ContentValues values = new ContentValues();
//                    values.put(DBManager.ColID, RecordIDLoad);
//                    values.put(DBManager.finished, "completePrivate");
//                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
//                    dbManager.Update(values, "id=?", SelectionArgsf);
//                    if (NotificationServicePrivate.nptificationPrivateISactive == true) {
//                        Intent serviceIntent = new Intent(context, NotificationServicePrivate.class);
//                        context.stopService(serviceIntent);
//                    }
//                }
                RefreshTab();
                dialog.hide();
            }
        });
        Button pending = mViewt.findViewById(R.id.epending);
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveData.LoadNotificationpandingData() == 1) {
                    pandingTask(0);
                }
                RefreshTab();
                dialog.hide();
            }
        });

    }
    //=View Task in daialog
    public void viewTaskPrivate() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
        View mViewt = getLayoutInflater().inflate(R.layout.view_task_dialog, null);
        mBuilder.setView(mViewt);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        TextView title = mViewt.findViewById(R.id.etitle);
        title.setText(saveData.LoadTitleNotifyPrivateDuplicate());
        TextView desc = mViewt.findViewById(R.id.edesc);
        desc.setText(saveData.LoadDescNotifyPrivateDuplicat());

        final Button cancel = (Button) mViewt.findViewById(R.id.ecencle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String RecordIDLoad = String.valueOf(saveData.checkduplicatidLoadPrivate());

                    saveData.SaveNotificationpandingDataprivate(0);
                    ContentValues values = new ContentValues();
                    values.put(DBManager.ColID, RecordIDLoad);
                    values.put(DBManager.finished, "canceledPrivate");
                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
                    dbManager.Update(values, "id=?", SelectionArgsf);
                    if (NotificationServicePrivate.nptificationPrivateISactive == true) {
                        Intent serviceIntent = new Intent(context, NotificationServicePrivate.class);
                        context.stopService(serviceIntent);
                    }

                RefreshTab();
                dialog.hide();
            }
        });
        Button complete = mViewt.findViewById(R.id.ecomplete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String RecordIDLoad = String.valueOf(saveData.checkduplicatidLoadPrivate());
                    saveData.checkidSave(Integer.parseInt(RecordIDLoad));
                    saveData.SaveNotificationpandingDataprivate(0);
                    ContentValues values = new ContentValues();
                    values.put(DBManager.ColID, RecordIDLoad);
                    values.put(DBManager.finished, "completePrivate");
                    String[] SelectionArgsf = {String.valueOf(RecordIDLoad)};
                    dbManager.Update(values, "id=?", SelectionArgsf);
                    if (NotificationServicePrivate.nptificationPrivateISactive == true) {
                        Intent serviceIntent = new Intent(context, NotificationServicePrivate.class);
                        context.stopService(serviceIntent);
                    }

                RefreshTab();
                dialog.hide();
            }
        });
        Button pending = mViewt.findViewById(R.id.epending);
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    pandingTask(11);

                RefreshTab();
                dialog.hide();
            }
        });

    }

    public static List<customItem> UpdateLis;
    static String RecordIDLoad;

    public static void pandingTask(int i) {
        UpdateLis = new ArrayList<>();
        //loadid
        if(i==0) {
            RecordIDLoad = String.valueOf(saveData.checkduplicatidLoad());
        }else{
            RecordIDLoad = String.valueOf(saveData.checkduplicatidLoadPrivate());
        }
        //sarch
        String[] SelectionArgs = {"%" + "%"};


        //clear after load
        UpdateLis.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor = dbManager.query(null, "task_name LIke ? ", SelectionArgs, DBManager.task_name);

        if (cursor.moveToFirst()) {
            String tableData = "";
            do {
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/


                //adaptor
                if (cursor.getString(cursor.getColumnIndex(DBManager.ColID)).equals(RecordIDLoad)) {
                    UpdateLis.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
                            , cursor.getString(cursor.getColumnIndex(DBManager.task_name))
                            , cursor.getString(cursor.getColumnIndex(DBManager.task_desc))
                            , cursor.getString(cursor.getColumnIndex(DBManager.yy))
                            , cursor.getString(cursor.getColumnIndex(DBManager.mm))
                            , cursor.getString(cursor.getColumnIndex(DBManager.dd))
                            , cursor.getString(cursor.getColumnIndex(DBManager.hh))
                            , cursor.getString(cursor.getColumnIndex(DBManager.min))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ss))
                            , cursor.getString(cursor.getColumnIndex(DBManager.repet))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ntfy))
                            , cursor.getString(cursor.getColumnIndex(DBManager.finished))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ntfState))
                            , cursor.getString(cursor.getColumnIndex(DBManager.listview))
                    ));
                }
            } while ((cursor.moveToNext()));
            //   Toast.makeText(,tableData,Toast.LENGTH_LONG).show();


        }

        if(i==0){
            updateDataOnPendingTask();
        }else {
            updateDataOnPendingTaskPrivate();
        }


    }

    public static void updateDataOnPendingTask() {
        Intent cloaseNotification = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(cloaseNotification);


        Intent intent = new Intent(context, pandingDataUpdate.class);


        intent.putExtra("name", UpdateLis.get(0).task_name);
        intent.putExtra("dsc", UpdateLis.get(0).task_desc);
        intent.putExtra("yy", UpdateLis.get(0).yy);
        intent.putExtra("mm", UpdateLis.get(0).mm);
        intent.putExtra("dd", UpdateLis.get(0).dd);
        intent.putExtra("hh", UpdateLis.get(0).hh);
        intent.putExtra("min", UpdateLis.get(0).min);
        intent.putExtra("id", RecordIDLoad);
        intent.putExtra("check", 0);
        context.startActivity(intent);
    }
    public static void updateDataOnPendingTaskPrivate() {
        Intent cloaseNotification = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(cloaseNotification);


        Intent intent = new Intent(context, pandingDataUpdate.class);


        intent.putExtra("name", UpdateLis.get(0).task_name);
        intent.putExtra("dsc", UpdateLis.get(0).task_desc);
        intent.putExtra("yy", UpdateLis.get(0).yy);
        intent.putExtra("mm", UpdateLis.get(0).mm);
        intent.putExtra("dd", UpdateLis.get(0).dd);
        intent.putExtra("hh", UpdateLis.get(0).hh);
        intent.putExtra("min", UpdateLis.get(0).min);
        intent.putExtra("id", RecordIDLoad);
        intent.putExtra("check", 1);
        context.startActivity(intent);
    }


    void progressListcreate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        View view = getLayoutInflater().inflate(R.layout.progres_dialog, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    // reat app =========================
    public  AlertDialog.Builder RateApp() {
        androidx.appcompat.app.AlertDialog.Builder mBuilder=new AlertDialog.Builder(context);
        mBuilder.setTitle("Dear User")
                .setMessage("If you like our app,please take a moment to rate it in Play Store." +
                        "\n\n5-star ratings encourage other users to download it!" +
                        "\n\nRating takes less then a minute.\nThanks for your support!")
                .setPositiveButton("5 STAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reatApp();
                        int f=saveData.RateLoadAgain()+7;
                        saveData.RateSave(f);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mBuilder.create();
        mBuilder.show();
        return mBuilder;


    }

    public void reatApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + "to_do_reminder.EspritSoft")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/aps/details?id=" + "to_do_reminder.EspritSoft")));
        }
    }
}
