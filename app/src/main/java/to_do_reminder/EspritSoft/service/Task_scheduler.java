package to_do_reminder.EspritSoft.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
import to_do_reminder.EspritSoft.Notification.AppNotify;
import to_do_reminder.EspritSoft.Notification.add;
import to_do_reminder.EspritSoft.Notification.cancle;
import to_do_reminder.EspritSoft.Notification.complete;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.activity.NewTask;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.saveData.saveSeting;

public class Task_scheduler extends Service {
    static Context context;
    public static String RecordID;
    saveData SaveData;
    saveSeting SaveSeting;
    public Task_scheduler() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    private NotificationManagerCompat notificationManagerCompat;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       context=this;
        SaveData=new saveData(this);
        SaveSeting=new saveSeting(context);

        notificationManagerCompat=NotificationManagerCompat.from(this);





        Intent notificationIntent =new Intent(this, Home.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,
                0,notificationIntent,0);



        RemoteViews collapesView = new RemoteViews(getPackageName(), R.layout.forground_notification);

          if(saveData.todayLoad() !=0 ){
            collapesView.setTextViewText(R.id.taskNumber, String.valueOf(saveData.todayLoad()));
        }else{
              collapesView.setTextViewText(R.id.taskNumber, "No");
        }
        collapesView.setTextViewText(R.id.upcomingTask, saveData.LoadTimeNextTask());
//        collapesView.setTextViewText(R.id.tomorrow, String.valueOf(saveData.tomorrowLoad()));
//        collapesView.setTextViewText(R.id.total, String.valueOf(saveData.totalLoad()));

        Intent clickIntent2 = new Intent(this, add.class);
        PendingIntent add = PendingIntent.getBroadcast(this,
                0, clickIntent2, 0);
        collapesView.setOnClickPendingIntent(R.id.add, add);

        collapesView.setOnClickPendingIntent(R.id.clops, pendingIntent);
        Notification notification=new NotificationCompat.Builder (this,AppNotify.CHANNEL_3_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setCustomContentView(collapesView)
                .build();


        if (saveSeting.statusbarLoad() == 1) {
            startForeground(3, notification);  //this is hiden or auto service starting when coment out this line
        }

        checkdaychenge();

        return START_STICKY;
    }

    void checkdaychenge(){
        final String d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Integer.parseInt(d) != saveData.dayLoad()){
                    Intent setrt=new Intent(context, CheckTotalTask.class);
                    startService(setrt);
                    saveData.daySave(Integer.parseInt(d));

                }

                checkdaychenge();
            }
        },60000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        Intent intent = new Intent(context, MyReceiver.class);
//        intent.setAction("Service_Destory");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent setr=new Intent(context, Task_scheduler.class);
                startService(setr);
            }
        },5000);

    }

    public static void newTask(){
        Intent cloaseNotification=new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(cloaseNotification);

        Intent intent = new Intent(context, NewTask.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("check", 0);
        context.startActivity(intent);
    }


}
