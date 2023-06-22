package to_do_reminder.EspritSoft.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;


public class CheckTotalTask extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public List<customItem> mDatalist;
    public DBManager dbManager;
    static Context context;
    saveData saveData;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context=this;
        saveData=new saveData(this);
        dbManager = new DBManager(context);

        mDatalist = new ArrayList<>();
        allDataSort();

        return super.onStartCommand(intent, flags, startId);
    }

    //===============================================



    String y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
    String m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
    String d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
    String hh=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
    String mm=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());

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

        if(mDatalist.size() >0){

        }else{
            saveData.saveTimeNextTask("No Task Found");
        }

        saveData.totalSave(total);saveData.todaySave(today);saveData.tomorrowSave(tomorrow);
//        Intent setrtstpo=new Intent(this, Task_scheduler.class);
//        stopService(setrtstpo);
        Intent setrt=new Intent(this, Task_scheduler.class);
        startService(setrt);

        Intent setr2=new Intent(context, CheckTotalTask.class);
        stopService(setr2);

    }


}
