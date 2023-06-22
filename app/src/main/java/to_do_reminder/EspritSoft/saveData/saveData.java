package to_do_reminder.EspritSoft.saveData;

import android.content.Context;
import android.content.SharedPreferences;

public class saveData {

    static SharedPreferences Shredref;

    public saveData(Context context) {
        Shredref = context.getSharedPreferences("myRef", Context.MODE_PRIVATE);
    }
    public static void SaveData(int position){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("position",position);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadData(){
        int FileContent= (int) Shredref.getInt("position", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }

    public static void SaveST(int voice){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("voiceBlth",voice);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadST(){
        int FileContent= (int) Shredref.getInt("voiceBlth", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void SavePg(int PG){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("PG",PG);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadPg(){
        int FileContent= (int) Shredref.getInt("PG", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }

    public static void  SaveprivateTab(String HsName) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("Password",HsName);
        editor.commit();
    }

    public static String LoadPrivateTab() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("Password","1234");
        return FileContent;
    }
    public static void  SaveprivateTabSecurity(String tab3sec) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("tab3sec",tab3sec);
        editor.commit();
    }

    public static String LoadPrivateTabSecurity() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("tab3sec","1234");
        return FileContent;
    }
    public static void  SaveTab4(String album) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("album",album);
        editor.commit();
    }

    public static String LoadTab4() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("album","No album");
        return FileContent;
    }

    public static void  SavTab5(String p) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("p",p);
        editor.commit();
    }

    public static String LoadTab5() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("p","No Song");
        return FileContent;
    }
    public static void  SavTab6(String tb6) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("tb6",tb6);
        editor.commit();
    }

    public static String LoadTab6() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("tb6","No Song");
        return FileContent;
    }
    public static void  SavTab7(String tb7) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("tb7",tb7);
        editor.commit();
    }

    public static String LoadTab7() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("tb7","No Song");
        return FileContent;
    }
    public static void  SavTab8(String tb8) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("tb8",tb8);
        editor.commit();
    }

    public static String LoadTab8() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("tb8","No Song");
        return FileContent;
    }
    public static void  SavTab9(String tb9) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("tb9",tb9);
        editor.commit();
    }

    public static String LoadTab9() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("tb9","No Song");
        return FileContent;
    }
    // check private mode active or not
    public static void chSavetab3(int chtab3){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("chtab3",chtab3);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int chLoadtab3(){
        int FileContent= (int) Shredref.getInt("chtab3", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
//========== !st login TaskList save
public static void TasKListsav(int TasKListsav){
    SharedPreferences.Editor editor=Shredref.edit();
    editor.putInt("TasKListsav",TasKListsav);
    // editor.putString("Password",Password);
    editor.commit();
}
    public static int TasklistLoad(){
        int FileContent= (int) Shredref.getInt("TasKListsav", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //============= check id
    public static void checkidSave(int checkid){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checkid",checkid);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkidLoad(){
        int FileContent= (int) Shredref.getInt("checkid", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void checkduplicatidSave(int checkduplicatidSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checkduplicatidSave",checkduplicatidSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkduplicatidLoad(){
        int FileContent= (int) Shredref.getInt("checkduplicatidSave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //============= check id
    public static void checkidSavePrivate(int checkidSavePrivate){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checkidSavePrivate",checkidSavePrivate);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkidLoadPrivate(){
        int FileContent= (int) Shredref.getInt("checkidSavePrivate", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void checkduplicatidSavePrivate(int checkduplicatidSavePrivate){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checkduplicatidSavePrivate",checkduplicatidSavePrivate);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkduplicatidLoadPrivate(){
        int FileContent= (int) Shredref.getInt("checkduplicatidSavePrivate", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //=============check private task
    public static void checTaskSave(int checTask){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checTask",checTask);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkTaskprivateLoad(){
        int FileContent= (int) Shredref.getInt("checTask", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void checduplicatTaskPrivateSave(int checduplicatTaskPrivateSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("checduplicatTaskPrivateSave",checduplicatTaskPrivateSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int checkduplicatTaskprivateLoad(){
        int FileContent= (int) Shredref.getInt("checduplicatTaskPrivateSave", 0);
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
//============== check list name
public static void  SavListName(String SavListName) {
    SharedPreferences.Editor editor = Shredref.edit();
    // editor.putString("state", HsName);
    editor.putString("SavListName",SavListName);
    editor.commit();
}

    public static String LoadListName() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("SavListName","Empty");
        return FileContent;
    }
    // check notification is panding
    public static void SaveNotificationpandingData(int Notificationpanding){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("Notificationpanding",Notificationpanding);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadNotificationpandingData(){
        int FileContent= (int) Shredref.getInt("Notificationpanding", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    // check notificationPrivate is panding
    public static void SaveNotificationpandingDataprivate(int SaveNotificationpandingDataprivate){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("SaveNotificationpandingDataprivate",SaveNotificationpandingDataprivate);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int LoadNotificationpandingDataprivate(){
        int FileContent= (int) Shredref.getInt("SaveNotificationpandingDataprivate", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //title and description notification
    public static void  SavTitleNotify(String title) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("title",title);
        editor.commit();
    }

    public static String LoadTitleNotify() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("title","Empty");
        return FileContent;
    }
    public static void  SavDescNotify(String description) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("description",description);
        editor.commit();
    }

    public static String LoadDescNotify() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("description","Empty");
        return FileContent;
    }
    public static void  SavTitleNotifyDuplicat(String SavTitleNotifyDuplicat) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("SavTitleNotifyDuplicat",SavTitleNotifyDuplicat);
        editor.commit();
    }

    public static String LoadTitleNotifyDuplicat() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("SavTitleNotifyDuplicat","Empty");
        return FileContent;
    }
    public static void  SavDescNotifyDuplicat(String SavDescNotifyDuplicat) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("SavDescNotifyDuplicat",SavDescNotifyDuplicat);
        editor.commit();
    }

    public static String LoadDescNotifyDuplicate() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("SavDescNotifyDuplicat","Empty");
        return FileContent;
    }
    public static void  SavTitleNotifyPrivate(String titledup) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("titledup",titledup);
        editor.commit();
    }

    public static String LoadTitleNotifyPrivate() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("titledup","Empty");
        return FileContent;
    }
    public static void  SavDescNotifyPrivate(String descriptiondupli) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("descriptiondupli",descriptiondupli);
        editor.commit();
    }

    public static String LoadDescNotifyPrivate() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("descriptiondupli","Empty");
        return FileContent;
    }
    //
    public static void  SavTitleNotifyPrivateDuplicate(String SavTitleNotifyPrivateDuplicate) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("SavTitleNotifyPrivateDuplicate",SavTitleNotifyPrivateDuplicate);
        editor.commit();
    }

    public static String LoadTitleNotifyPrivateDuplicate() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("SavTitleNotifyPrivateDuplicate","Empty");
        return FileContent;
    }
    public static void  SavDescNotifyPrivateDuplicat(String SavDescNotifyPrivateDuplicat) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("SavDescNotifyPrivateDuplicat",SavDescNotifyPrivateDuplicat);
        editor.commit();
    }

    public static String LoadDescNotifyPrivateDuplicat() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("SavDescNotifyPrivateDuplicat","Empty");
        return FileContent;
    }

    //=========================== forground servis view
    public static void todaySave(int todaySave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("todaySave",todaySave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int todayLoad(){
        int FileContent= (int) Shredref.getInt("todaySave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void tomorrowSave(int tomorrowSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("tomorrowSave",tomorrowSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int tomorrowLoad(){
        int FileContent= (int) Shredref.getInt("tomorrowSave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void totalSave(int totalSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("totalSave",totalSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int totalLoad(){
        int FileContent= (int) Shredref.getInt("totalSave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void StatusNotifySave(int StatusNotifySave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("StatusNotifySave",StatusNotifySave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int StatusNotifyLoad(){
        int FileContent= (int) Shredref.getInt("StatusNotifySave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //==================== day save
    public static void daySave(int daySave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("daySave",daySave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int dayLoad(){
        int FileContent= (int) Shredref.getInt("daySave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    //======================
    //============== next task time
    public static void  saveTimeNextTask(String saveTimeNextTask) {
        SharedPreferences.Editor editor = Shredref.edit();
        // editor.putString("state", HsName);
        editor.putString("saveTimeNextTask",saveTimeNextTask);
        editor.commit();
    }

    public static String LoadTimeNextTask() {
        // String FileContent= Shredref.getString("state", "Not Set yeat");
        String FileContent =Shredref.getString("saveTimeNextTask","No Task Found");
        return FileContent;
    }
    //==================== Rating System
    public static void RateSave(int RateSave){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("RateSave",RateSave);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int RateLoad(){
        int FileContent= (int) Shredref.getInt("RateSave", Integer.parseInt("0"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
    public static void RateSaveAgain(int RateSaveAgain){
        SharedPreferences.Editor editor=Shredref.edit();
        editor.putInt("RateSaveAgain",RateSaveAgain);
        // editor.putString("Password",Password);
        editor.commit();
    }
    public static int RateLoadAgain(){
        int FileContent= (int) Shredref.getInt("RateSaveAgain", Integer.parseInt("7"));
        //   FileContent+="Password:"+Shredref.getString("Password","No Password");
        return FileContent;
    }
}
