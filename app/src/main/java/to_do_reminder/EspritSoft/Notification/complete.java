package to_do_reminder.EspritSoft.Notification;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;


public class complete extends BroadcastReceiver {
    saveData SaveData;
    @Override
    public void onReceive(Context context, Intent intent) {
        SaveData=new saveData(context);

            NotificationService.complete();


    }
}
