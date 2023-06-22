package to_do_reminder.EspritSoft.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import to_do_reminder.EspritSoft.activity.NewTask;

public class panding extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationService.pandingTask();
//        Intent intent5 = new Intent(context, NewTask.class);
//        context.startActivity(intent5);
    }
}
