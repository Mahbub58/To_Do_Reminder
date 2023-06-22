package to_do_reminder.EspritSoft.service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import to_do_reminder.EspritSoft.Notification.NotificationService;
import to_do_reminder.EspritSoft.Notification.NotificationServicePrivate;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;


public class MyReceiver extends BroadcastReceiver {

    public static DBManager dbManager;
    public static String RecordID;
    saveData SaveData;
    @Override
    public void onReceive(Context context, Intent intent) {
        dbManager = new DBManager(context);
        SaveData=new saveData(context);

        // TODO: This method is called when the BroadcastReceiver is receiving

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();


        // an Intent broadcast.
        if(intent.getAction().equalsIgnoreCase("to_do_reminder.EspritSoft")){

//            Intent serviceIntent = new Intent(context, CheckTask.class);
//            context.startService(serviceIntent);
            if(saveData.checkTaskprivateLoad()==0){
                Intent serviceIntent = new Intent(context, NotificationService.class);
                ContextCompat.startForegroundService(context,serviceIntent);
            }else{
                Intent serviceIntent = new Intent(context, NotificationServicePrivate.class);
                ContextCompat.startForegroundService(context,serviceIntent);
            }
           saveData.todaySave(saveData.todayLoad()-1);
            saveData.totalSave(saveData.totalLoad()-1);

            Intent serviceIntent = new Intent(context, Task_scheduler.class);
            ContextCompat.startForegroundService(context,serviceIntent);

        }
        else if(intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            //save time load heare
            Intent serviceIntent = new Intent(context, manageData.class);
            context.startService(serviceIntent);
        }
        wl.release();

    }


}
