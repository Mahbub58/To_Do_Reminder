package to_do_reminder.EspritSoft.service;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;

import static to_do_reminder.EspritSoft.activity.Home.RecordID;
import static to_do_reminder.EspritSoft.activity.Home.dbManager;


public class manageData extends Service {
    private static Context context;
    public manageData(){

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

      ArrayList<String>TaskList;
    public static List<customItem> mDatalist;
    public static DBManager dbManager;
    public static String RecordID;
    saveData SaveData;
BroadcastReceiver broadcastReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = this;
        dbManager = new DBManager(context);
        SaveData=new saveData(this);

//        IntentFilter intentFilter=new IntentFilter("com.alredy.alarm");
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(broadcastReceiver,intentFilter);

        //add menufest file     <service android:name=".service.manageData"/>
        //min mathod
        TaskList=new ArrayList<String>();
        LoadTaskListName();

        mDatalist = new ArrayList<>();

        allDataSort();



        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    String y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
    String m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
    String d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
    String hh=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
    String mm=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
    int dld=Integer.parseInt(d)+1;
    int today=0,tomorrow=0,total=0;

    //============== work
    public  void allDataSort() {


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
                          total++;
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
                    total++;
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
                    total++;
                    if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.dd)))>Integer.parseInt(d)&&
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBManager.dd)))<Integer.parseInt(d)+2){
                        tomorrow++;
                    }
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
                    today++;total++;
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
                    today++;total++;
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

      saveData.totalSave(total);saveData.todaySave(today);saveData.tomorrowSave(tomorrow);
        Intent setrt=new Intent(this, Task_scheduler.class);
        startService(setrt);


    }





    public  void setDataAlarm() {
        //   Toast.makeText(context, Integer.parseInt(mDatalist.get(0).hh) + ":" + Integer.parseInt(mDatalist.get(0).min), Toast.LENGTH_SHORT).show();

        if (mDatalist.size() > 0) {

//            Intent serviceSetRemainder=new Intent(context, setaRemainder.class);
//            startService(serviceSetRemainder);

            //call setRemainder service
//           Task_scheduler task_scheduler=new Task_scheduler();
//           task_scheduler.setRemainder(Integer.parseInt(mDatalist.get(0).yy),Integer.parseInt(mDatalist.get(0).mm),Integer.parseInt(mDatalist.get(0).dd),
//                   Integer.parseInt(mDatalist.get(0).hh),Integer.parseInt(mDatalist.get(0).min),Integer.parseInt(mDatalist.get(0).ID),
//                   mDatalist.get(0).task_name,mDatalist.get(0).task_desc);

//            setaRemainder.setRemainder(Integer.parseInt(mDatalist.get(0).yy),Integer.parseInt(mDatalist.get(0).mm),Integer.parseInt(mDatalist.get(0).dd),
//                    Integer.parseInt(mDatalist.get(0).hh),Integer.parseInt(mDatalist.get(0).min),Integer.parseInt(mDatalist.get(0).ID),
//                    mDatalist.get(0).task_name,mDatalist.get(0).task_desc);


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
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }


           //setUpcoming task time
            upcomingTask();


            RecordID = mDatalist.get(0).ID;

//            saveData.checkidSave(Integer.parseInt(RecordID));
//           saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);

            //check the task is private or not and sve true or false 1 or 0 to feature check
            for(int i=0;i<TaskList.size();i++){
                if(TaskList.get(i).equals(mDatalist.get(0).listview)){
                    switch (i){
                        case 0:
                            saveData.checTaskSave(0);
                            saveData.checkidSave(Integer.parseInt(RecordID));
                            saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            break;
                        case 1:
                            saveData.checTaskSave(1);
                            saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                            saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            break;
                        case 2:
                            if(saveData.LoadTab4().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 3:
                            if(saveData.LoadTab5().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 4:
                            if(saveData.LoadTab6().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 5:
                            if(saveData.LoadTab7().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                        case 6:
                            if(saveData.LoadTab8().equals("Enable")){
                                saveData.checTaskSave(1);
                                saveData.checkidSavePrivate(Integer.parseInt(RecordID));
                                saveData.SavTitleNotifyPrivate(mDatalist.get(0).task_name);saveData.SavDescNotifyPrivate(mDatalist.get(0).task_desc);
                            }else{
                                saveData.checTaskSave(0);
                                saveData.checkidSave(Integer.parseInt(RecordID));
                                saveData.SavTitleNotify(mDatalist.get(0).task_name);saveData.SavDescNotify(mDatalist.get(0).task_desc);
                            }
                            break;
                    }
                }
            }


            Intent serviceIntent = new Intent(context, manageData.class);
            context.stopService(serviceIntent);

        }else{
            Intent intent = new Intent(context, MyReceiver.class);
            intent.setAction("to_do_reminder.EspritSoft");
           // intent.putExtra("MyMessage", "Hello From Alarm");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 23433, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, Integer.parseInt("3020"));
            int MM=Integer.parseInt("3");
            calendar.set(Calendar.MONTH, MM-1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt("31"));

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt("12"));
            calendar.set(Calendar.MINUTE, Integer.parseInt("12"));
            calendar.set(Calendar.SECOND, 5);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }




            Intent serviceIntent = new Intent(context, manageData.class);
            context.stopService(serviceIntent);

        }
    }

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

    public  void LoadTaskListName(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task",null);
        Type type =new TypeToken<ArrayList<String >>() {}.getType();
        TaskList=gson.fromJson(json,type);

    }
}




