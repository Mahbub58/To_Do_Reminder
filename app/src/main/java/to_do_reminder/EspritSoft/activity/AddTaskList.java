package to_do_reminder.EspritSoft.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;

import static to_do_reminder.EspritSoft.activity.Home.context;
import static to_do_reminder.EspritSoft.activity.Home.dbManager;

public class AddTaskList extends AppCompatActivity {
    EditText addlistname;
    CheckBox checkbox;
    saveData SaveData;
    int stt=0;
    TextView title;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_list);
        SaveData=new saveData(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addlistname=findViewById(R.id.addlist_name);
        checkbox=findViewById(R.id.privatemodeCheked);

        title=findViewById(R.id.tasklistheader);

        Intent intent = getIntent();
        addlistname.setText(intent.getStringExtra("name"));
        stt=intent.getIntExtra("stt",0);
        title.setText(intent.getStringExtra("title"));


        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void submitList(View view) {
        if(!addlistname.getText().toString().isEmpty()) {
            if (stt==0) {
                if (Home.TaskList.size() < 7) {
                    Home.TaskList.add(addlistname.getText().toString());
                    saveArray();
                    saveData.SaveST(1);
                  //  Home.loadpermision = 1;

                    if(checkbox.isChecked()){
                        int tb=Home.TaskList.size();
                        if(tb==3){
                         saveData.SaveTab4("Enable");
                        }else if(tb==4){
                            saveData.SavTab5("Enable");
                        }else if(tb==5){
                            saveData.SavTab6("Enable");
                        }else if(tb==6){
                            saveData.SavTab7("Enable");
                        }else if(tb==7){
                            saveData.SavTab8("Enable");
                        }
                    }else{
                        int tb=Home.TaskList.size();
                        if(tb==3){
                            saveData.SaveTab4("Disable");
                        }else if(tb==4){
                            saveData.SavTab5("Disable");
                        }else if(tb==5){
                            saveData.SavTab6("Disable");
                        }else if(tb==6){
                            saveData.SavTab7("Disable");
                        }else if(tb==7){
                            saveData.SavTab8("Disable");
                        }
                    }
                    NewTask.newTab=1;
                    Home.newTablist=1;
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    intent.putExtra("check", 1);
                    startActivity(intent);
                    NewTask.refreshNewtTask();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "List is full", Toast.LENGTH_SHORT).show();
                }
            } else {

                //rename listview data
                String slistname=Home.TaskList.get(stt);
                String[]SelectionArgs={"%"+slistname+"%"};
                Cursor cursor=dbManager.query(null,"listview LIke ? ",SelectionArgs, DBManager.listview);
                if(cursor.moveToFirst()) {
                    do{
                        ContentValues values=new ContentValues();
                        values.put(DBManager.listview,addlistname.getText().toString());
                        String[] SelectionArgss={String.valueOf(slistname)};
                        dbManager.Update(values,"listview=?",SelectionArgss);


                    } while (cursor.moveToNext());

                }
                //rename task
                Home.TaskList.set(stt, addlistname.getText().toString());
                saveArray();
                saveData.SaveST(1);

                if(checkbox.isChecked()) {
                    int tb = stt;
                    if (tb == 2) {
                        saveData.SaveTab4("Enable");
                    } else if (tb == 3) {
                        saveData.SavTab5("Enable");
                    } else if (tb == 4) {
                        saveData.SavTab6("Enable");
                    } else if (tb == 5) {
                        saveData.SavTab7("Enable");
                    } else if (tb == 6) {
                        saveData.SavTab8("Enable");
                    }
                }else{
                    int tb = stt;
                    if (tb == 2) {
                        saveData.SaveTab4("Disable");
                    } else if (tb == 3) {
                        saveData.SavTab5("Disable");
                    } else if (tb == 4) {
                        saveData.SavTab6("Disable");
                    } else if (tb == 5) {
                        saveData.SavTab7("Disable");
                    } else if (tb == 6) {
                        saveData.SavTab8("Disable");
                    }
                }

                Home.loadpermision = 1;
                NewTask.newTab=1;
                Home.newTablist=1;
                Intent intent=new Intent(getApplicationContext(),Home.class);
                intent.putExtra("check", 1);
                startActivity(intent);
                RenameTask.list();
                finish();

            }

        }else {
            Toast.makeText(getApplicationContext(),"List name can not be empty",Toast.LENGTH_LONG).show();
        }

    }

    //save and load tabName list array
    public void saveArray(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(Home.TaskList);
        editor.putString("task",json);
        editor.apply();
    }


}
