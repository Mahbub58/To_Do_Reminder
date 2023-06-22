package to_do_reminder.EspritSoft.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import to_do_reminder.EspritSoft.activity.NewTask;
import to_do_reminder.EspritSoft.service.Task_scheduler;

public class add extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Task_scheduler.newTask();

    }
}
