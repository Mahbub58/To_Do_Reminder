package to_do_reminder.EspritSoft.saveData;

import android.content.Context;
import android.content.SharedPreferences;

public class saveTasktempData {

    static SharedPreferences Shredref;

    public saveTasktempData(Context context) {
        Shredref = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }
    public static void SaveButton(int position){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("position",position);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadButton(){
        int FileContent= (int) Shredref.getInt("position", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void Saveyy(int Saveyy){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Saveyy",Saveyy);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int Loadyy(){
        int FileContent= (int) Shredref.getInt("Saveyy", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void Savemm(int Savemm){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Savemm",Savemm);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int Loadmm(){
        int FileContent= (int) Shredref.getInt("Savemm", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void Savedd(int Savedd){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Savedd",Savedd);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int Loaddd(){
        int FileContent= (int) Shredref.getInt("Savedd", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void Savehh(int Savehh){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Savehh",Savehh);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int Loadhh(){
        int FileContent= (int) Shredref.getInt("Savehh", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void Savemin(int Savemin){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Savemin",Savemin);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int Loadmin(){
        int FileContent= (int) Shredref.getInt("Savemin", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
//======panding task
public static void pSaveButton(int pposition){
    SharedPreferences.Editor editor=Shredref.edit();
    editor.putInt("pposition",pposition);
    // editor.putString("Password",Password);
    editor.commit();
}
    public static int pLoadButton(){
        int FileContent= (int) Shredref.getInt("pposition", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void pSaveyy(int pSaveyy){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("pSaveyy",pSaveyy);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int pLoadyy(){
        int FileContent= (int) Shredref.getInt("pSaveyy", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void pSavemm(int Savemm){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Savemm",Savemm);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int pLoadmm(){
        int FileContent= (int) Shredref.getInt("pSavemm", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void pSavedd(int pSavedd){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("pSavedd",pSavedd);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int pLoaddd(){
        int FileContent= (int) Shredref.getInt("Savedd", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void pSavehh(int pSavehh){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("pSavehh",pSavehh);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int pLoadhh(){
        int FileContent= (int) Shredref.getInt("pSavehh", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void pSavemin(int pSavemin){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("pSavemin",pSavemin);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int pLoadmin(){
        int FileContent= (int) Shredref.getInt("pSavemin", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }



}
