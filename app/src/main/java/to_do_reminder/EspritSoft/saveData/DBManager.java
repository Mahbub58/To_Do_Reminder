package to_do_reminder.EspritSoft.saveData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName = "Student";
    static final String TableName = "Task";
    public static final String task_name = "task_name";
    public static final String task_desc = "task_desc";
    public static final String listview = "listview";
    public static final String yy = "yy";
    public static final String mm ="mm";

    public static final String dd = "dd";
    public static final String hh = "hh";
    public static final String min ="min";
    public static final String ss ="ss";
    public static final String repet ="repet";
    public static final String ntfy ="ntfy";
    public static final String finished ="due";
    public static final String ntfState ="Normal";
    public static final String ColID = "ID";
    static final int DBVersion = 1;
    //create table Logine(ID integer primary key autoincrment,UserName text,Passwoord text)

    static final String CreateTable = "Create table IF NOT EXISTS " + TableName +
            "(ID integer PRIMARY KEY AUTOINCREMENT," + ss + " text,"+ ntfState + " text," + finished + " text," + ntfy + " text," + repet + " text," + min + " text," + hh + " text," + dd + " text," + mm + " text," + yy + " text,"  + listview + " text," + task_name + " text," + task_desc + " text );";
    static class DatabaseHelperUser extends SQLiteOpenHelper {
        Context context;

        DatabaseHelperUser(Context context) {
            super(context, DBName, null, DBVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
           // Toast.makeText(context, "Table is created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("Drop table IF EXISTS " + TableName);
            onCreate(db);
        }
    }


    public DBManager(Context context) {
        DatabaseHelperUser db = new DatabaseHelperUser(context);
        sqlDB = db.getWritableDatabase();
    }

    public long Insert(ContentValues values) {
        long ID = sqlDB.insert(TableName, "", values);

        return ID;
    }

    //select user name ,password from Logins where ID=1;
    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TableName);

        Cursor cursor = qb.query(sqlDB, Projection, Selection, SelectionArgs, null, null, SortOrder);
        return cursor;
    }

    public int Delet(String Selection, String[] SelectionArgs){
        int count=sqlDB.delete(TableName,Selection,SelectionArgs);
        return count;
    }

    public int Update(ContentValues values, String Selection, String[] SelectionArgs){
        int count=sqlDB.update(TableName,values,Selection,SelectionArgs);
        return count;
    }


}