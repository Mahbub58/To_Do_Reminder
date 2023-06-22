package to_do_reminder.EspritSoft.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import to_do_reminder.EspritSoft.AditionalSystem.CheckTabeActivity;
import to_do_reminder.EspritSoft.AditionalSystem.RefreshActivity;
import to_do_reminder.EspritSoft.Notification.NotificationService;
import to_do_reminder.EspritSoft.Notification.NotificationServicePrivate;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.Tab.Tab1;
import to_do_reminder.EspritSoft.Tab.Tab2;
import to_do_reminder.EspritSoft.Tab.Tab3;
import to_do_reminder.EspritSoft.Tab.Tab4;
import to_do_reminder.EspritSoft.Tab.Tab5;
import to_do_reminder.EspritSoft.Tab.Tab6;
import to_do_reminder.EspritSoft.Tab.Tab7;
import to_do_reminder.EspritSoft.Tab.Tab8;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.saveData.saveTasktempData;
import to_do_reminder.EspritSoft.service.MyReceiver;
import to_do_reminder.EspritSoft.service.manageData;

public class pandingDataUpdate extends AppCompatActivity {
    static EditText etuser;
    static EditText etpass;
    static String RecordID;
     static TextView get_repet,textdemo;
    private static Context context;
    Spinner get_ntfy,get_lis,ntfyStatus;
    static String get_year,get_month,get_day,get_hour,get_minute,repet,ntfytype,listtype,ntfystate;
    static int check=0;
    private ExpandableLayout expandableLayout1;
    static String yy,mm,dd,hh,min;
    static EditText timepicker,datepicker;
    static Button save;
    saveData SaveData;
    static int checked=0;
    saveTasktempData saveTaskData;
    public static DBManager dbManager;
    static ArrayList<String> TaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panding_data_update);
        context=this;
        SaveData=new saveData(this);
        saveTaskData=new saveTasktempData(this);
        dbManager = new DBManager(context);

        TaskList=new ArrayList<String>();
        LoadTaskListName();
        etuser=findViewById(R.id.etuser);
        etpass=findViewById(R.id.etpass);


        Intent intent=getIntent();
        etpass.setText(intent.getStringExtra("dsc"));
        etuser.setText(intent.getStringExtra("name"));
        yy = intent.getStringExtra("yy");
        mm = intent.getStringExtra("mm");
        dd = intent.getStringExtra("dd");
        hh = intent.getStringExtra("hh");
        min = intent.getStringExtra("min");
        RecordID = intent.getStringExtra("id");
        checked=intent.getIntExtra("check",0);



        init();
        setTimeDateFirstOpen();

    }
    static String y;static String m;static String d;static String h;static String mi;
    Toolbar toolbar;TextView home;
    void init(){
        y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
        d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        h=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
        mi=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.hname);
        home.setText("Pending Task");
        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       timepicker=findViewById(R.id.ptimepicker);
        datepicker=findViewById(R.id.pdatepicker);
        timepicker.setText(hh+":"+min);
        datepicker.setText(yy+"-"+mm+"-"+dd);

        get_lis=findViewById(R.id.listtype);

        sniper();
        save=findViewById(R.id.buSave);
        save.setEnabled(false);
        saveButton();



        //hide keyboard
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            timepicker.setShowSoftInputOnFocus(false);
            datepicker.setShowSoftInputOnFocus(false);
        }
        etuser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count!=0){
                    saveButton();
                }else {
                    save.setEnabled(false);
                }
                saveButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        timepicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0&&count<5){
                    DialogFragment newFragment = new pandingDataUpdate.TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");

                }if(before==1){
                    DialogFragment newFragment = new pandingDataUpdate.TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");
                }
                hidekeyboard();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        datepicker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count>0 && count<8) {
                    DialogFragment newFragment = new pandingDataUpdate.DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }if(before==1){
                    DialogFragment newFragment = new pandingDataUpdate.DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }
                hidekeyboard();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        timepicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP==event.getAction()){
                    DialogFragment newFragment = new pandingDataUpdate.TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");
                    hidekeyboard();


                }

                return false;
            }
        });
        datepicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP==event.getAction()){
                    DialogFragment newFragment = new pandingDataUpdate.DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                    hidekeyboard();
                }

                return false;
            }
        });


    }
    //======================= frist open set time date ==============
    void setTimeDateFirstOpen(){

            datepicker.setText(yy+"-"+mm+"-"+dd);
            home.setText("Update Task");
            //lanscae mode load
            if(saveTasktempData.pLoadyy()!=0){
                get_year=String.valueOf(saveTasktempData.pLoadyy());
            }else{
                get_year=yy;
            }
            if(saveTasktempData.pLoadmm()!=0){
                get_month=String.valueOf(saveTasktempData.pLoadmm());
            }else{
                get_month=mm;
            }
            if(saveTasktempData.pLoaddd()!=0){
                get_day=String.valueOf(saveTasktempData.pLoaddd());
            }else{
                get_day=dd;
            }
            if(saveTasktempData.pLoadhh()!=0){
                get_hour=String.valueOf(saveTasktempData.pLoadhh());
            }else{
                get_hour=hh;
            }
            if(saveTasktempData.pLoadmin()!=0){
                get_minute=String.valueOf(saveTasktempData.pLoadmin());
            }else{
                get_minute=min;
            }

        setTimeDateFirstin();
    }

    void hidekeyboard(){
        View view=this.getCurrentFocus();
        if(view !=null){
            InputMethodManager inm=(InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    //======================== set time and date========================
    void setTimeDateFirstin(){
        //set Date
            datepicker.setText(yy+"-"+mm+"-"+dd);
        //time stet==================
        if (DateFormat.is24HourFormat(context)) {
                String time = hh + ":" + min;
                timepicker.setText(time);
        }else{

                if (Integer.parseInt(hh) == 0) {
                    String time = "12" + ":" + min + " AM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 12) {
                    String time = "12" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 13) {
                    String time = "1" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 14) {
                    String time = "2" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 15) {
                    String time = "3" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 16) {
                    String time = "4" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 17) {
                    String time = "5" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 18) {
                    String time = "6" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 19) {
                    String time = "7" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 20) {
                    String time = "8" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 21) {
                    String time = "9" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 22) {
                    String time = "10" + ":" + min + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(hh) == 23) {
                    String time = "11" + ":" + min + " PM";
                    timepicker.setText(time);
                } else {
                    String time = hh + ":" + min + " AM";
                    timepicker.setText(time);
                }
        }
        datecheck();
        saveButton();
    }
    static int sTime=0;
    static void saveButton(){

        if(!timepicker.getText().toString().isEmpty()&& !datepicker.getText().toString().isEmpty() && !etuser.getText().toString().isEmpty()
          && sTime==1){
            save.setEnabled(true);
            saveTasktempData.pSaveButton(1);
        }else if (saveTasktempData.pLoadButton()==1){
            save.setEnabled(true);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put(DBManager.task_name, etuser.getText().toString());
                values.put(DBManager.task_desc, etpass.getText().toString());
                values.put(DBManager.mm, get_month);
                values.put(DBManager.yy, get_year);
                values.put(DBManager.dd, get_day);
                values.put(DBManager.hh, get_hour);
                values.put(DBManager.min, get_minute);
                // values.put(DBManager.ss, ss);
                values.put(DBManager.repet, "yes");
                values.put(DBManager.ntfy, ntfytype);
                values.put(DBManager.listview, listtype);
                values.put(DBManager.finished, "du");
                values.put(DBManager.ntfState, ntfystate);
                // values.put(DBManager.ColID,RecordID);
                String[] SelectionArgs={String.valueOf(RecordID)};
                dbManager.Update(values,"id=?",SelectionArgs);


                Intent serviceIntent = new Intent(context, manageData.class);
                context.startService(serviceIntent);

                saveTasktempData.pSaveyy(0); saveTasktempData.pSavemm(0); saveTasktempData.pSavedd(0);
                saveTasktempData.pSavehh(0); saveTasktempData.pSavemin(0); saveTasktempData.pSaveButton(0);
                //notification pandingData Home screen alart false

                if (checked == 0) {
                    saveData.SaveNotificationpandingData(0);
                    if(NotificationService.notificationISactive==true) {
                            Intent notification = new Intent(context, NotificationService.class);
                            context.stopService(notification);
                    }
                } else {
                    saveData.SaveNotificationpandingDataprivate(0);
                    if(NotificationServicePrivate.nptificationPrivateISactive==true) {
                            Intent notification = new Intent(context, NotificationServicePrivate.class);
                            context.stopService(notification);

                    }
                }
                RefreshActivity.finishActivity((AppCompatActivity) context);
            }
        });
    }
    static void datecheck() {

        if (Integer.parseInt(get_year) > Integer.parseInt(y)) {
            sTime=1;
            saveButton();
            datepicker.setTextColor(context.getResources().getColor(R.color.green));
            timepicker.setTextColor(context.getResources().getColor(R.color.green));
        } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
                && Integer.parseInt(get_month) > Integer.parseInt(m)) {
            sTime=1;
            saveButton();
            datepicker.setTextColor(context.getResources().getColor(R.color.green));
            timepicker.setTextColor(context.getResources().getColor(R.color.green));
        } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
                && Integer.parseInt(get_month) == Integer.parseInt(m)
                && Integer.parseInt(get_day) > Integer.parseInt(d)) {
            sTime=1;
            saveButton();
            datepicker.setTextColor(context.getResources().getColor(R.color.green));
            timepicker.setTextColor(context.getResources().getColor(R.color.green));
        } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
                && Integer.parseInt(get_month) == Integer.parseInt(m)
                && Integer.parseInt(get_day) == Integer.parseInt(d)) {
            datepicker.setTextColor(context.getResources().getColor(R.color.green));
            //time
            if (Integer.parseInt(get_hour) > Integer.parseInt(h)) {
                sTime=1;
                saveButton();
                timepicker.setTextColor(context.getResources().getColor(R.color.green));
            } else if (Integer.parseInt(get_hour) == Integer.parseInt(h)
                    && Integer.parseInt(get_minute) > Integer.parseInt(mi)) {
                sTime=1;
                saveButton();
                timepicker.setTextColor(context.getResources().getColor(R.color.green));
            } else {
                sTime=0;
                timepicker.setTextColor(context.getResources().getColor(R.color.red));
            }
        } else {
            sTime=0;
            saveButton();
            datepicker.setTextColor(context.getResources().getColor(R.color.red));
            timepicker.setTextColor(context.getResources().getColor(R.color.red));
        }


    }

    //=============================================================== finishActivity==========
    @Override
    public void finish() {
        saveTasktempData.pSaveyy(0); saveTasktempData.pSavemm(0); saveTasktempData.pSavedd(0);
        saveTasktempData.pSavehh(0); saveTasktempData.pSavemin(0); saveTasktempData.pSaveButton(0);
        super.finish();
    }

    //================================================================ time date picker class ============

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user

            get_hour = String.valueOf(hourOfDay);
            get_minute = String.valueOf(minute);

            //save for landScapemode temper data
            saveTasktempData.pSavehh(hourOfDay);
            saveTasktempData.pSavemin(minute);

            if (DateFormat.is24HourFormat(context)) {
                String time = hourOfDay + ":" + minute;
                timepicker.setText(time);
            } else {
                if (hourOfDay == 0) {
                    String time = "12" + ":" + minute + " AM";
                    timepicker.setText(time);
                } else if (hourOfDay == 12) {
                    String time = "12" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 13) {
                    String time = "1" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 14) {
                    String time = "2" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 15) {
                    String time = "3" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 16) {
                    String time = "4" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 17) {
                    String time = "5" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 18) {
                    String time = "6" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 19) {
                    String time = "7" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 20) {
                    String time = "8" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 21) {
                    String time = "9" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 22) {
                    String time = "10" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else if (hourOfDay == 23) {
                    String time = "11" + ":" + minute + " PM";
                    timepicker.setText(time);
                } else {
                    String time = hourOfDay + ":" + minute + " AM";
                    timepicker.setText(time);
                }
            }

            saveButton();
            datecheck();
        }
    }

//========date picker==

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);

            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            get_year=String.valueOf(year);
            get_month=String.valueOf(month+1);
            get_day=String.valueOf(day);

            String date=year+"-"+get_month+"-"+day;
            datepicker.setText(date);
            //saver for landscapeMode temper data
            saveTasktempData.pSaveyy(year); saveTasktempData.pSavemm(month+1); saveTasktempData.pSavedd(day);

            saveButton();
            datecheck();

        }
    }

    //============snipper ===========================
    void sniper(){

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,TaskList);
        get_lis.setAdapter(arrayAdapter);

        get_lis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //you can get acces here

                listtype=TaskList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    //================== TaskList Load
    public  void LoadTaskListName(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task",null);
        Type type =new TypeToken<ArrayList<String >>() {}.getType();
        TaskList=gson.fromJson(json,type);

    }
}

