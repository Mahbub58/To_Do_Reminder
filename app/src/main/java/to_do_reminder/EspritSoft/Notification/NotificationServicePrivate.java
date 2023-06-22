package to_do_reminder.EspritSoft.Notification;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.AditionalSystem.sortdd;
import to_do_reminder.EspritSoft.AditionalSystem.sorthh;
import to_do_reminder.EspritSoft.AditionalSystem.sortmin;
import to_do_reminder.EspritSoft.AditionalSystem.sortmm;
import to_do_reminder.EspritSoft.AditionalSystem.sortyy;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.activity.pandingDataUpdate;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.service.MyReceiver;


public class NotificationServicePrivate extends Service {
    static boolean ntf=true;
    static boolean ntt=true;
   static int a=2;
   public static int SPLASH_TIME_OUT=1000;
    private static Context context;

    private NotificationManagerCompat notificationManagerCompat;
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public static List<customItem> mDatalist;
    public static DBManager dbManager;
    public static String RecordID;
    public static String RecordIDLoad;
    saveData SaveData;
    ArrayList<String>TaskList;
    public static boolean nptificationPrivateISactive=false;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input=intent.getStringExtra("inputExtra");
        notificationManagerCompat=NotificationManagerCompat.from(this);

        nptificationPrivateISactive=true;


        Intent notificationIntent =new Intent(this, Home.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,
                0,notificationIntent,0);



           RemoteViews collapesView = new RemoteViews(getPackageName(), R.layout.notification_clopsed_private);
//           RemoteViews expendedViews = new RemoteViews(getPackageName(), R.layout.notification_expandable_private);

       //complete btn
        Intent clickIntent = new Intent(this, complete.class);
        PendingIntent complete = PendingIntent.getBroadcast(this,
                0, clickIntent, 0);
//        expendedViews.setOnClickPendingIntent(R.id.ecomplete, complete);
//        collapesView.setOnClickPendingIntent(R.id.ccomplete, complete);

        //cancle btn
        Intent clickIntent2 = new Intent(this, cancle.class);
        PendingIntent cancle = PendingIntent.getBroadcast(this,
                0, clickIntent2, 0);
//        expendedViews.setOnClickPendingIntent(R.id.ecencle, cancle);
//        collapesView.setOnClickPendingIntent(R.id.ccncle, cancle);
        //my Job


           // collapesView.setTextViewText(R.id.cllops_1, "Hello world");

      //  expendedViews.setImageViewResource(R.id.imageview, R.drawable.til);
      //  expendedViews.setTextViewText(R.id.expend_1, "Hellow!!");


//        expendedViews.setOnClickPendingIntent(R.id.expand, pendingIntent);
        collapesView.setOnClickPendingIntent(R.id.clops, pendingIntent);


        Notification notification=new NotificationCompat.Builder (this,AppNotify.CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setCustomContentView(collapesView)
                .setCustomHeadsUpContentView(collapesView) //pop_up
                //  .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                //Vibration
                //.setVibrate(new long[] {  1000, 1000, 1000,1000 })
                 //LED
                .setLights(Color.RED, 3000, 3000)
                .build();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
             // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        //ton
            try {
                //defult sound
                //Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                //customsound
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                soundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.never);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), soundUri);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
      //  notificationManagerCompat.notify(2,notification);


        startForeground(2,notification);  //this is hiden or auto service starting when coment out this line

       // Toast.makeText(this,"Run",Toast.LENGTH_SHORT).show();



        //worl
        context = this;
        dbManager = new DBManager(context);
        SaveData=new saveData(this);
        //add menufest file     <service android:name=".service.manageData"/>
        //min mathod
       //taskList
        TaskList=new ArrayList<String>();
        LoadTaskListName();


        mDatalist = new ArrayList<>();

        saveData.SaveNotificationpandingDataprivate(11);

       allDataSort();



        return START_NOT_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }




void finishlist() {

//    new Handler().postDelayed(new Runnable() {
//        @Override
//        public void run() {
//            Intent serviceIntent = new Intent(context, manageData.class);
//            context.startService(serviceIntent);
//        }
//    },100);






}

    //============== work
    public  void allDataSort() {
        String y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        String m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
        String d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String hh=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
        String mm=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());

        //sarch
        //    String[]SelectionArgs={"%"+etsearch.getText().toString()+"%"};

        String[] SelectionArgs = {"%" + "%"};


        //clear after load
        mDatalist.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor = dbManager.query(null, "task_name LIke ? ", SelectionArgs, DBManager.task_name);

        if (cursor.moveToFirst()) {
            String tableData = "";
            do {
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/
                //adaptor

                if (cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.yy)))>Integer.parseInt(y)){
                    mDatalist.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
                }else if (cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.yy)))==Integer.parseInt(y)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.mm)))>Integer.parseInt(m)){
                    mDatalist.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
                }else if(cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.yy)))==Integer.parseInt(y)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.mm)))==Integer.parseInt(m)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.dd)))>Integer.parseInt(d)){
                    mDatalist.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
                }else if(cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.yy)))==Integer.parseInt(y)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.mm)))==Integer.parseInt(m)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.dd)))==Integer.parseInt(d)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.hh)))>Integer.parseInt(hh)){
                    mDatalist.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
                }else if (cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.yy)))==Integer.parseInt(y)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.mm)))==Integer.parseInt(m)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.dd)))==Integer.parseInt(d)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.hh)))==Integer.parseInt(hh)
                        && Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.min)))>Integer.parseInt(mm)){
                    mDatalist.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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

        //========
        if(mDatalist.size() >0) {
            Collections.sort(mDatalist, new sortmin());
            Collections.sort(mDatalist, new sorthh());
            Collections.sort(mDatalist, new sortdd());
            Collections.sort(mDatalist, new sortmm());
            Collections.sort(mDatalist, new sortyy());
        }
        setDataAlarm();
    }




    public  void setDataAlarm() {
        //   Toast.makeText(context, Integer.parseInt(mDatalist.get(0).hh) + ":" + Integer.parseInt(mDatalist.get(0).min), Toast.LENGTH_SHORT).show();

        if (mDatalist.size() > 0) {

            Intent intent = new Intent(context, MyReceiver.class);
            intent.setAction("to_do_reminder.EspritSoft");
            intent.putExtra("MyMessage", "Hello From Alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 23433, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, Integer.parseInt(mDatalist.get(0).yy));
            int MM=Integer.parseInt(mDatalist.get(0).mm);
            calendar.set(Calendar.MONTH, MM-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(mDatalist.get(0).dd));

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mDatalist.get(0).hh));
            calendar.set(Calendar.MINUTE, Integer.parseInt(mDatalist.get(0).min));
            calendar.set(Calendar.SECOND, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
              //  Toast.makeText(context, "Alarm>kitkat", Toast.LENGTH_SHORT).show();
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
               // Toast.makeText(context, "Alarm<kitkat", Toast.LENGTH_SHORT).show();
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

//upcoming task time
            upcomingTask();

            RecordID = mDatalist.get(0).ID;

            //check the task is private or not and sve true or false 1 or 0 to feature check
            for(int i=0;i<TaskList.size();i++){
                if(TaskList.get(i).equals(mDatalist.get(0).listview)){
                    switch (i){
                        case 0:
                            saveData.checTaskSave(0);
                            saveData.checkduplicatidSave(saveData.checkidLoad());
                            saveData.checkidSave(Integer.parseInt(RecordID));
                            saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                            saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            break;
                        case 1:
                            saveData.checTaskSave(1);
                            saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                            saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                            saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                            saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);

                            break;
                        case 2:
                            if(saveData.LoadTab4().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkduplicatidSave(saveData.checkidLoad());
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 3:
                            if(saveData.LoadTab5().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkduplicatidSave(saveData.checkidLoad());
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 4:
                            if(saveData.LoadTab6().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkduplicatidSave(saveData.checkidLoad());
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 5:
                            if(saveData.LoadTab7().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkduplicatidSave(saveData.checkidLoad());
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 6:
                            if(saveData.LoadTab8().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkduplicatidSave(saveData.checkidLoad());
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                    }
                }
            }


        }else{
            Intent intent = new Intent(context, MyReceiver.class);
            intent.setAction("com.alredy.alarm");
            intent.putExtra("MyMessage", "Hello From Alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 23433, intent, 0);
            AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, Integer.parseInt("3020"));
            int MM=Integer.parseInt("4");
            calendar.set(Calendar.MONTH, MM-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt("31"));

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt("12"));
            calendar.set(Calendar.MINUTE, Integer.parseInt("12"));
            calendar.set(Calendar.SECOND, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }

//            saveData.checkduplicatidSave(saveData.checkidLoad());
//            saveData.checduplicatTaskPrivateSave(saveData.checkTaskprivateLoad());
//            saveData.SavTitleNotifyDuplicate(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());

            saveData.checTaskSave(0);
            saveData.checkduplicatidSave(saveData.checkidLoad());
            saveData.SavTitleNotifyDuplicat(saveData.LoadTitleNotify());saveData.SavDescNotifyDuplicat(saveData.LoadDescNotify());

            saveData.checTaskSave(1);
            saveData.checkduplicatidSavePrivate(saveData.checkidLoadPrivate());
            saveData.SavTitleNotifyPrivateDuplicate(saveData.LoadTitleNotifyPrivate());saveData.SavDescNotifyPrivateDuplicat(saveData.LoadDescNotifyPrivate());

            saveData.saveTimeNextTask("No Task Found");
        }
    }


    //================== TaskList Load
    public  void LoadTaskListName(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task",null);
        Type type =new TypeToken<ArrayList<String >>() {}.getType();
        TaskList=gson.fromJson(json,type);

    }
    //================== upcoming task
    private void upcomingTask() {
        //date
        String date="";
        int position=0;
        if(Integer.parseInt(mDatalist.get(position).mm)==1){
            date=("Jan" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==2){
            date=("Feb" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==3){
            date=("Mar" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==4){
            date=("Apr" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==5){
            date=("May" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==6){
            date=("Jun" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==7){
            date=("jul" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==8){
            date=("Aug" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==9){
            date=("Sep" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==10){
            date=("Oct" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==11){
            date=("Nov" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==12){
            date=("Dec" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }
        //time
        String time="";
        if (DateFormat.is24HourFormat(context)) {

        }else{
            if (Integer.parseInt(mDatalist.get(position).hh) == 0) {
                time=("12" + ":" + mDatalist.get(position).min+" AM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 12) {
                time=("12" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 13) {
                time=("1" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 14) {
                time=("2" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 15) {
                time=("3" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 16) {
                time=("4" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 17) {
                time=("5" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 18) {
                time=("6" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 19) {
                time=("7" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 20) {
                time=("8" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 21) {
                time=("9" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 22) {
                time=("10" + ":" + mDatalist.get(position).min+" PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 23) {
                time=("11" + ":" + mDatalist.get(position).min+" PM");
            }else{
                time=(mDatalist.get(position).hh+ ":" + mDatalist.get(position).min+" AM");
            }
        }
        saveData.saveTimeNextTask(time+" "+date);
    }
}
