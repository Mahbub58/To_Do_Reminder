package to_do_reminder.EspritSoft.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

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



public class NewTask extends AppCompatActivity {
   static EditText etuser;
   static EditText etpass;
   static String RecordID;
   static EditText timepicker,datepicker;
   static  TextView get_repet;
   private static Context context;
   static  Spinner get_ntfy,get_lis,ntfyStatus;
   static String get_year,get_month,get_day,get_hour,get_minute,repet,ntfytype,listtype,ntfystate;
   static int check=0;
    private ExpandableLayout expandableLayout1;
   static String yy,mm,dd,hh,min;
    saveData SaveData;
    saveTasktempData saveTaskData;
    public static DBManager dbManager;
    static ArrayList<String>TaskList;
    static Button save;
    Toolbar toolbar;
    TextView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        context=this;
        SaveData=new saveData(this);
        saveTaskData=new saveTasktempData(this);
        dbManager = new DBManager(context);

        TaskList=new ArrayList<String>();
        LoadTaskListName();
        etuser=findViewById(R.id.etuser);
        etpass=findViewById(R.id.etpass);

        Intent intent=getIntent();
        check=intent.getIntExtra("check",0);
            etuser.setText(intent.getStringExtra("name"));
           etpass.setText(intent.getStringExtra("dsc"));
            yy = intent.getStringExtra("yy");
            mm = intent.getStringExtra("mm");
            dd = intent.getStringExtra("dd");
            hh = intent.getStringExtra("hh");
            min = intent.getStringExtra("min");
            RecordID = intent.getStringExtra("id");




        init();
        expandableLayout();
       setTimeDateFirstOpen();

    }

   static String y;
   static String m;
   static String d;
   static String h;
   static String mi;

    void init(){
        y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
        d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        h=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
        mi=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.hname);
        home.setText("New Task");
        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    timepicker=findViewById(R.id.timepicker);
    datepicker=findViewById(R.id.datepicker);




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
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");

                }if(before==1){
                    DialogFragment newFragment = new TimePickerFragment();
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
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }if(before==1){
                    DialogFragment newFragment = new DatePickerFragment();
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
               DialogFragment newFragment = new TimePickerFragment();
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
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                hidekeyboard();
            }

            return false;
        }
    });

    get_ntfy=findViewById(R.id.ntfytyp);
        ntfyStatus=findViewById(R.id.ntfystate);

    get_repet=findViewById(R.id.repettyp);
    get_lis=findViewById(R.id.listtype);

    sniper();

    save=findViewById(R.id.buSave);
        save.setEnabled(false);
    saveButton();
    }
    //======================= frist open set time date ==============
  void setTimeDateFirstOpen(){
      if(check==1){
//          timepicker.setText(hh+":"+min);
          datepicker.setText(yy+"-"+mm+"-"+dd);
          home.setText("Update Task");
          //lanscae mode load
          if(saveTasktempData.Loadyy()!=0){
              get_year=String.valueOf(saveTasktempData.Loadyy());
          }else{
              get_year=yy;
          }
          if(saveTasktempData.Loadmm()!=0){
              get_month=String.valueOf(saveTasktempData.Loadmm());
          }else{
              get_month=mm;
          }
          if(saveTasktempData.Loaddd()!=0){
              get_day=String.valueOf(saveTasktempData.Loaddd());
          }else{
              get_day=dd;
          }
          if(saveTasktempData.Loadhh()!=0){
              get_hour=String.valueOf(saveTasktempData.Loadhh());
          }else{
              get_hour=hh;
          }
          if(saveTasktempData.Loadmin()!=0){
              get_minute=String.valueOf(saveTasktempData.Loadmin());
          }else{
              get_minute=min;
          }

      }else{
          //lanscae mode load
          if(saveTasktempData.Loadyy()!=0){
              get_year=String.valueOf(saveTasktempData.Loadyy());
          }else{
              get_year=y;
          }
          if(saveTasktempData.Loadmm()!=0){
              get_month=String.valueOf(saveTasktempData.Loadmm());
          }else{
              get_month=m;
          }
          if(saveTasktempData.Loaddd()!=0){
              get_day=String.valueOf(saveTasktempData.Loaddd());
          }else{
              get_day=d;
          }
          if(saveTasktempData.Loadhh()!=0){
              get_hour=String.valueOf(saveTasktempData.Loadhh());
          }else{
              get_hour=h;
          }
          if(saveTasktempData.Loadmin()!=0){
              get_minute=String.valueOf(saveTasktempData.Loadmin());
          }else{
              get_minute=mi;
          }
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
        if(check==1){
            datepicker.setText(yy+"-"+mm+"-"+dd);
        }else{
            datepicker.setText(y+"-"+m+"-"+d);
        }
        //time stet==================
        if (DateFormat.is24HourFormat(context)) {
            if(check==1) {
                String time = h + ":" + mi;
                timepicker.setText(time);
            }else{ String time = hh + ":" + min;
                timepicker.setText(time);
            }
        }else{
            if(check==1) {
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
            }else{
                if (Integer.parseInt(h) == 0) {
                    String time = "12" + ":" + mi + " AM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 12) {
                    String time = "12" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 13) {
                    String time = "1" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 14) {
                    String time = "2" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 15) {
                    String time = "3" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 16) {
                    String time = "4" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 17) {
                    String time = "5" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 18) {
                    String time = "6" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 19) {
                    String time = "7" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 20) {
                    String time = "8" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 21) {
                    String time = "9" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 22) {
                    String time = "10" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else if (Integer.parseInt(h) == 23) {
                    String time = "11" + ":" + mi + " PM";
                    timepicker.setText(time);
                } else {
                    String time = h + ":" + mi + " AM";
                    timepicker.setText(time);
                }
            }
        }
        datecheck();
        saveButton();
    }


   static void saveButton(){

        if(!timepicker.getText().toString().isEmpty()&& !datepicker.getText().toString().isEmpty() && !etuser.getText().toString().isEmpty()){
            save.setEnabled(true);
            saveTasktempData.SaveButton(1);
        }else if (saveTasktempData.LoadButton()==1){
            save.setEnabled(true);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check==0) {

                    ContentValues values = new ContentValues();
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
                    long id = dbManager.Insert(values);
                    if (id > 0) {

                    } else {
                        Toast.makeText(context, "Error Please clear app data", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // check=1;
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

                }

                Intent serviceIntent = new Intent(context, manageData.class);
                context.startService(serviceIntent);
//
//        manageData manageData=new manageData();
//        manageData.allDataSort();


                if(newTab==1){
//                    newTab=0;
//                    Home.RefreshActivity();
                    RefreshActivity.finishActivity((AppCompatActivity) context);
                }else{
                    if(TaskList.get(0).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab2activityNotNull==true){
                            Tab2.LoadElement();
                        }
                    }else if(TaskList.get(1).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab3activityNotNull==true){
                            Tab3.LoadElement();
                        }
                    }else if(TaskList.get(2).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab4activityNotNull==true){
                            Tab4.LoadElement();
                        }
                    }else if(TaskList.get(3).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab5activityNotNull==true){
                            Tab5.LoadElement();
                        }
                    }else if(TaskList.get(4).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab6activityNotNull==true){
                            Tab6.LoadElement();
                        }
                    }else if(TaskList.get(5).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab7activityNotNull==true){
                            Tab7.LoadElement();
                        }
                    }else if(TaskList.get(6).equals(listtype)){
                        if(CheckTabeActivity.tab1activityNotNull==true){
                            Tab1.LoadElement();
                        }
                        if(CheckTabeActivity.tab8activityNotNull==true){
                            Tab8.LoadElement();
                        }
                    }

                }
                saveTasktempData.Saveyy(0); saveTasktempData.Savemm(0); saveTasktempData.Savedd(0);
                saveTasktempData.Savehh(0); saveTasktempData.Savemin(0); saveTasktempData.SaveButton(0);


                    RefreshActivity.finishActivity((AppCompatActivity) context);


            }
        });
    }


//============================= check date and time============

static void datecheck() {

    if (Integer.parseInt(get_year) > Integer.parseInt(y)) {
        datepicker.setTextColor(context.getResources().getColor(R.color.green));
        timepicker.setTextColor(context.getResources().getColor(R.color.green));
    } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
            && Integer.parseInt(get_month) > Integer.parseInt(m)) {
        datepicker.setTextColor(context.getResources().getColor(R.color.green));
        timepicker.setTextColor(context.getResources().getColor(R.color.green));
    } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
            && Integer.parseInt(get_month) == Integer.parseInt(m)
            && Integer.parseInt(get_day) > Integer.parseInt(d)) {
        datepicker.setTextColor(context.getResources().getColor(R.color.green));
        timepicker.setTextColor(context.getResources().getColor(R.color.green));
    } else if (Integer.parseInt(get_year) == Integer.parseInt(y)
            && Integer.parseInt(get_month) == Integer.parseInt(m)
            && Integer.parseInt(get_day) == Integer.parseInt(d)) {
        datepicker.setTextColor(context.getResources().getColor(R.color.green));
        //time
        if (Integer.parseInt(get_hour) > Integer.parseInt(h)) {
            timepicker.setTextColor(context.getResources().getColor(R.color.green));
        } else if (Integer.parseInt(get_hour) == Integer.parseInt(h)
                && Integer.parseInt(get_minute) > Integer.parseInt(mi)) {
            timepicker.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            timepicker.setTextColor(context.getResources().getColor(R.color.red));
        }
    } else {
        datepicker.setTextColor(context.getResources().getColor(R.color.red));
        timepicker.setTextColor(context.getResources().getColor(R.color.red));
    }


}

//=============================================================== finishActivity==========
    @Override
    public void finish() {
        saveTasktempData.Saveyy(0); saveTasktempData.Savemm(0); saveTasktempData.Savedd(0);
        saveTasktempData.Savehh(0); saveTasktempData.Savemin(0); saveTasktempData.SaveButton(0);
//        if(newTab==1){
//            Home.RefreshActivity();}
        super.finish();
    }


    public static int newTab=0;


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
            get_hour=String.valueOf(hourOfDay);
            get_minute=String.valueOf(minute);
            //save for landScapemode temper data
            saveTasktempData.Savehh(hourOfDay); saveTasktempData.Savemin(minute);

            if (DateFormat.is24HourFormat(context)) {
                String time=hourOfDay+":"+minute;
                timepicker.setText(time);
            }else{
                if(hourOfDay==0){
                    String time="12"+":"+minute+" AM";
                    timepicker.setText(time);
                }else if(hourOfDay==12){
                    String time="12"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==13){
                    String time="1"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==14){
                    String time="2"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==15){
                    String time="3"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==16){
                    String time="4"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==17){
                    String time="5"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==18){
                    String time="6"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==19){
                    String time="7"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==20){
                    String time="8"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==21){
                    String time="9"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==22){
                    String time="10"+":"+minute+" PM";
                    timepicker.setText(time);
                }else if(hourOfDay==23){
                    String time="11"+":"+minute+" PM";
                    timepicker.setText(time);
                }else{
                    String time=hourOfDay+":"+minute+" AM";
                    timepicker.setText(time);
                }
            }

            saveButton();
            datecheck();
        }
    }




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
            saveTasktempData.Saveyy(year); saveTasktempData.Savemm(month+1); saveTasktempData.Savedd(day);

            saveButton();
            datecheck();
        }
    }
// refreshActivity==========
    static void refreshNewtTask(){
        RefreshActivity.recreatActivity((AppCompatActivity)context);
    }


    //============snipper ===========================
   void sniper(){

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,Home.TaskList);
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

        /**===ntfy type====**/
        final List<String> ntfy =new ArrayList<String>();
        ntfy.add("Sound & Vibrate");
        ntfy.add("Sound");
        ntfy.add("Vibrate");


        ArrayAdapter<String> antfyAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,ntfy);
        get_ntfy.setAdapter(antfyAdapter);

        get_ntfy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //you can get acces here
               // Toast.makeText(getApplicationContext(),ntfy.get(position),Toast.LENGTH_LONG).show();
                ntfytype=ntfy.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /**===ntfy state====**/
        final List<String> ntfyState =new ArrayList<String>();
        ntfyState.add("Low");
        ntfyState.add("Medium");
        ntfyState.add("High");


        ArrayAdapter<String> ntfyStateAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,ntfyState);
        ntfyStatus.setAdapter(ntfyStateAdapter);

        ntfyStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //you can get acces here
               // Toast.makeText(getApplicationContext(),ntfy.get(position),Toast.LENGTH_LONG).show();
                ntfystate=ntfyState.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //=====expandable layout ======
    void expandableLayout(){
        expandableLayout1 = findViewById(R.id.expandable_layout_1);
        expandableLayout1.collapse();
        TextView expandlayout=findViewById(R.id.expand_button);
        expandlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout1.isExpanded()) {
                    expandableLayout1.collapse();
                } else {
                    expandableLayout1.expand();
                }
            }
        });

        //expanfd lestner
        expandableLayout1.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                Log.d("ExpandableLayout1", "State: " + state);

             //   Toast.makeText(context,"HEY",Toast.LENGTH_SHORT).show();

            }
        });



    }
    //=================new TaskList Add ==============
    public void AddTaskList(View view) {
     Intent intent=new Intent(this,AddTaskList.class);
        intent.putExtra("stt","0");
        intent.putExtra("title","New List");
     startActivity(intent);
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






