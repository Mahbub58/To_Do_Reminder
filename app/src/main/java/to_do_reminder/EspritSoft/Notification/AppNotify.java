package to_do_reminder.EspritSoft.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import to_do_reminder.EspritSoft.R;

public class AppNotify extends Application {
    public static final String CHANNEL_1_ID="channel1";
    public static final String CHANNEL_2_ID="channel2";
    public static final String CHANNEL_3_ID="channel3";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        soundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.never);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Chaannel1",
                    NotificationManager.IMPORTANCE_HIGH
            );




            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Chaannel2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Chaannel3",
                    NotificationManager.IMPORTANCE_LOW
            );


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);

        }
    }
}
