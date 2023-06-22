package to_do_reminder.EspritSoft.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.saveData.saveData;

public class cancle extends BroadcastReceiver {
    saveData SaveData;
    @Override
    public void onReceive(Context context, Intent intent) {
        SaveData=new saveData(context);

      //  if(saveData.checkTaskprivateLoad()==0){
            NotificationService.cancelTask();
      //  }


    }
}
