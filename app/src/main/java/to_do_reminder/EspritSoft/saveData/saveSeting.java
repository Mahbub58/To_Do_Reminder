package to_do_reminder.EspritSoft.saveData;

import android.content.Context;
import android.content.SharedPreferences;

public class saveSeting {

    static SharedPreferences Shredref;

    public saveSeting(Context context) {
        Shredref = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }
    public static void statusBarSave(int statusBarSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("statusBarSave",statusBarSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int statusbarLoad(){
        int FileContent= (int) Shredref.getInt("statusBarSave", Integer.parseInt("1"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
}
