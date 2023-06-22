package to_do_reminder.EspritSoft.Tab;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTab;
import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTask;
import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.AditionalSystem.CheckTabeActivity;
import to_do_reminder.EspritSoft.AditionalSystem.sortdd;
import to_do_reminder.EspritSoft.AditionalSystem.sorthh;
import to_do_reminder.EspritSoft.AditionalSystem.sortmin;
import to_do_reminder.EspritSoft.AditionalSystem.sortmm;
import to_do_reminder.EspritSoft.AditionalSystem.sortyy;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.activity.NewTask;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.service.manageData;

import static to_do_reminder.EspritSoft.activity.Home.RecordID;
import static to_do_reminder.EspritSoft.activity.Home.dbManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab7 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab7.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab7 newInstance(String param1, String param2) {
        Tab7 fragment = new Tab7();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
View view;
    EditText etsearch;
    Button Add, search;

    EditText pass,wrpass,newpass,cfpass,chsecuritycode,newsecuritycode;
    Button  passwordsubmit,checkPassword,wrcheckPassword,checkSecuritysubmit;
    RelativeLayout verify,verify2,newSet,checksecuritycode;
    TextView forgate,incorrect_code;
    saveData SaveData;

    static CustomAdaptorAllTab adaptorAlltab;
    static RecyclerView reciclerview;
    public static boolean tab7IsNotNull=false;
    public static Context context;
    public static List<customItem> Tab7;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tab7, container, false);

        context = getContext();
        SaveData=new saveData(context);
        Tab7=new ArrayList<>();
        main();

        return view;
    }

    void main() {
       CheckTabeActivity.tab7activityNotNull=true;
        reciclerview = view.findViewById(R.id.recyclerView);

        verify=view.findViewById(R.id.verify);
        verify2=view.findViewById(R.id.verify2);
        newSet=view.findViewById(R.id.newset);
        checksecuritycode=view.findViewById(R.id.checksecuritycode);
        pass=view.findViewById(R.id.pass);
        checkPassword=view.findViewById(R.id.chckPassword);
        wrpass=view.findViewById(R.id.wrpass);
        wrcheckPassword=view.findViewById(R.id.wrchckPassword);
        newpass=view.findViewById(R.id.newpass);
        cfpass=view.findViewById(R.id.confpass);
        passwordsubmit=view.findViewById(R.id.Passwordsubmit);
        forgate=view.findViewById(R.id.forgate);
        chsecuritycode=view.findViewById(R.id.chsecuritycode);
        checkSecuritysubmit=view.findViewById(R.id.checkSecuritysubmit);
        newsecuritycode=view.findViewById(R.id.newsecuritycode);
        incorrect_code=view.findViewById(R.id.incarecetcode);

        if(saveData.LoadTab7().equals("Enable")){
            passwordSystem();
            reciclerview.setVisibility(View.INVISIBLE);
        }else {
            verify.setVisibility(View.INVISIBLE);
            newSet.setVisibility(View.INVISIBLE);
            reciclerview.setVisibility(View.VISIBLE);
            newSet.setVisibility(View.INVISIBLE);
            checksecuritycode.setVisibility(View.INVISIBLE);
        }
        LoadElement();

    }

    //password system
    void passwordSystem(){
        //int check=saveData.chLoadtab3();
        if(saveData.chLoadtab3()==0){
            verify.setVisibility(View.INVISIBLE);
            newSet.setVisibility(View.VISIBLE);
            passwordsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!newpass.getText().toString().isEmpty()&& ! newsecuritycode.getText().toString().isEmpty()) {
                    if(newpass.getText().toString().equals(cfpass.getText().toString())){
                        saveData.SaveprivateTab(newpass.getText().toString());
                        saveData.chSavetab3(1);
                        saveData.SaveprivateTabSecurity(newsecuritycode.getText().toString());
                        newSet.setVisibility(View.INVISIBLE);
                        passwordSystem();
                    }else{
                        Toast.makeText(context,"Password not match",Toast.LENGTH_SHORT).show();
                    }
                    }else {
                        Toast.makeText(context,"Password & security code can't be empty.",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            verify.setVisibility(View.VISIBLE);
            checkPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (saveData.LoadPrivateTab().equals(pass.getText().toString())) {
                        verify.setVisibility(View.INVISIBLE);
                        reciclerview.setVisibility(View.VISIBLE);
                    } else {
                        verify.setVisibility(View.INVISIBLE);
                        verify2.setVisibility(View.VISIBLE);
                    }
                }
            });
            wrcheckPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (saveData.LoadPrivateTab().equals(wrpass.getText().toString())) {
                        verify2.setVisibility(View.INVISIBLE);
                        reciclerview.setVisibility(View.VISIBLE);
                    } else {
                        wrpass.setText("");
                        wrpass.setHint("Wrong Password");
                    }
                }
            });
        }
        forgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify2.setVisibility(View.INVISIBLE);
                incorrect_code.setVisibility(View.INVISIBLE);
                checksecuritycode.setVisibility(View.VISIBLE);
            }
        });
        checkSecuritysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveData.LoadPrivateTabSecurity().equals(chsecuritycode.getText().toString())){
                    checksecuritycode.setVisibility(View.INVISIBLE);
                    saveData.chSavetab3(0);
                    passwordSystem();
                }else {
                    chsecuritycode.setText("");
                    chsecuritycode.setHint("Incorrect Code");
                    incorrect_code.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    public static void LoadElement(){

        //sarch
        String[]SelectionArgs={"%"+Home.sText+"%"};




        //clear after load
        Tab7.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor=dbManager.query(null,"task_name LIke ? ",SelectionArgs, DBManager.task_name);

        if(cursor.moveToFirst()){
            String tableData="";
            do{
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/
                //adaptor
                if(cursor.getString(cursor.getColumnIndex(DBManager.listview)).equals(Home.TaskList.get(5))
                        && cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")) {
                    Tab7.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
            }while ((cursor.moveToNext()));
            //   Toast.makeText(,tableData,Toast.LENGTH_LONG).show();


        }
        //sorting
        if(Tab7.size() >0) {
            Collections.sort(Tab7, new sortmin());
            Collections.sort(Tab7, new sorthh());
            Collections.sort(Tab7, new sortdd());
            Collections.sort(Tab7, new sortmm());
            Collections.sort(Tab7, new sortyy());
        }
        adaptorAlltab = new CustomAdaptorAllTab(context, Tab7, reciclerview);
        reciclerview.setAdapter(adaptorAlltab);
        reciclerview.setLayoutManager(new LinearLayoutManager(context));
        reciclerview.addItemDecoration(new DividerItemDecoration(reciclerview.getContext(), DividerItemDecoration.VERTICAL));
        loadAdaptor();
    }



    public static void loadAdaptor() {
        if (adaptorAlltab != null) {
            adaptorAlltab.setOnItemClickListner(new CustomAdaptorAllTab.OnItemClickListner() {
                @Override
                public void delete(int position) {
                    Alart(position,"Task will be deleted","Delete","Cancel",1);
                }

                @Override
                public void update(int position) {
                    Intent intent = new Intent(context, NewTask.class);
                    intent.putExtra("check", 1);
                    intent.putExtra("name", Tab7.get(position).task_name);
                    intent.putExtra("dsc", Tab7.get(position).task_desc);
                    intent.putExtra("yy", Tab7.get(position).yy);
                    intent.putExtra("mm", Tab7.get(position).mm);
                    intent.putExtra("dd", Tab7.get(position).dd);
                    intent.putExtra("hh", Tab7.get(position).hh);
                    intent.putExtra("min", Tab7.get(position).min);
                    intent.putExtra("id",Tab7.get(position).ID);
                    context.startActivity(intent);
                }
                int click=0;
                @Override
                public void complete(final int position) {
                    click++;
                    if(saveData.LoadTab7().equals("Enable")){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(click%2!=0){
                                    Home.RecordID = Tab7.get(position).ID;
                                    ContentValues values=new ContentValues();
                                    values.put(DBManager.finished,"completePrivate");
                                    values.put(DBManager.ColID,RecordID);
                                    String[] SelectionArgs={String.valueOf(RecordID)};
                                    dbManager.Update(values,"id=?",SelectionArgs);

                                    LoadElement();
                                }
                            }
                        },2000);
                    }else{
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(click%2!=0){
                                    Home.RecordID = Tab7.get(position).ID;
                                    ContentValues values=new ContentValues();
                                    values.put(DBManager.finished,"complete");
                                    values.put(DBManager.ColID,RecordID);
                                    String[] SelectionArgs={String.valueOf(RecordID)};
                                    dbManager.Update(values,"id=?",SelectionArgs);

                                    LoadElement();
                                }
                            }
                        },2000);
                    }
                }

                @Override
                public void cancel(int position) {
                    Alart(position,"Are you sure Task is canceled?","Yes","No",0);
                }

//                @Override
//                public void item(int position) {
//                    Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
//                }
            });
        }
    }
    public static AlertDialog.Builder Alart(final int position, String title, String actiontxt1,String actionyxy2, final int action) {
        androidx.appcompat.app.AlertDialog.Builder mBuilder=new AlertDialog.Builder(context);
        mBuilder.setTitle("Alart")
                .setMessage(title)
                .setPositiveButton(actiontxt1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (action == 1) {
                            if (saveData.LoadTab7().equals("Enable")) {
                                Home.RecordID = Tab7.get(position).ID;
                                ContentValues values = new ContentValues();
                                values.put(DBManager.finished, "deletedPrivate");
                                values.put(DBManager.ColID, RecordID);
                                String[] SelectionArgs = {String.valueOf(RecordID)};
                                dbManager.Update(values, "id=?", SelectionArgs);

                                LoadElement();

                            } else {
                                Home.RecordID = Tab7.get(position).ID;
                                ContentValues values = new ContentValues();
                                values.put(DBManager.finished, "deleted");
                                values.put(DBManager.ColID, RecordID);
                                String[] SelectionArgs = {String.valueOf(RecordID)};
                                dbManager.Update(values, "id=?", SelectionArgs);

                                LoadElement();

                            }

                        }else {
                            if (saveData.LoadTab4().equals("Enable")) {
                                Home.RecordID = Tab7.get(position).ID;
                                ContentValues values = new ContentValues();
                                values.put(DBManager.finished, "canceledPrivate");
                                values.put(DBManager.ColID, RecordID);
                                String[] SelectionArgs = {String.valueOf(RecordID)};
                                dbManager.Update(values, "id=?", SelectionArgs);

                                LoadElement();

                            } else {
                                Home.RecordID = Tab7.get(position).ID;
                                ContentValues values = new ContentValues();
                                values.put(DBManager.finished, "canceled");
                                values.put(DBManager.ColID, RecordID);
                                String[] SelectionArgs = {String.valueOf(RecordID)};
                                dbManager.Update(values, "id=?", SelectionArgs);

                                LoadElement();

                            }
                        }
                        //set alarm
                        Intent serviceIntent = new Intent(context, manageData.class);
                        context.startService(serviceIntent);
                    }
                })
                .setNegativeButton(actionyxy2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mBuilder.create();
        mBuilder.show();
        return mBuilder;

    }
}
