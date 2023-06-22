package to_do_reminder.EspritSoft.AditionalSystem;


import androidx.appcompat.app.AppCompatActivity;

public class RefreshActivity {


    public static void finishActivity(AppCompatActivity appCompatActivity) {
        appCompatActivity.finish();
    }

    public static void recreatActivity(AppCompatActivity appCompatActivity) {
        appCompatActivity.recreate();
    }
    public static void reActive(AppCompatActivity appCompatActivity) {
        appCompatActivity.finish();
        appCompatActivity.recreate();
    }
}
