package to_do_reminder.EspritSoft.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;

import static to_do_reminder.EspritSoft.activity.Home.RecordID;
import static to_do_reminder.EspritSoft.activity.Home.context;
import static to_do_reminder.EspritSoft.activity.Home.dbManager;

public class RenameTask extends AppCompatActivity {
Toolbar toolbar;
 static TextView tab1,tab2,tab3,tab4,tab5,tab6,tab7,tab8;
  static RelativeLayout layouttab3,layouttab4,layouttab5,layouttab6,layouttab7,layouttab8;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    saveData SaveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_task);
        context = this;
        SaveData=new saveData(this);
        toolbar();




        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        tab3=findViewById(R.id.tab3);
        tab4=findViewById(R.id.tab4);
        tab5=findViewById(R.id.tab5);
        tab6=findViewById(R.id.tab6);
        tab7=findViewById(R.id.tab7);
        tab8=findViewById(R.id.tab8);



        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkList(2,1);
            }
        });
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(3,1);
            }
        });
        tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(4,1);
            }
        });
        tab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(5,1);
            }
        });
        tab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(6,1);
            }
        });
        tab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(7,1);
            }
        });


        layouttab3=findViewById(R.id.layouttab3);
        layouttab4=findViewById(R.id.layouttab4);
        layouttab5=findViewById(R.id.layouttab5);
        layouttab6=findViewById(R.id.layouttab6);
        layouttab7=findViewById(R.id.layouttab7);
        layouttab8=findViewById(R.id.layouttab8);



     list();
     deletelistButon();


    }
    void toolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView hname=findViewById(R.id.hname);
        hname.setText("All List");
        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

   static void list(){
       layouttab3.setVisibility(View.INVISIBLE);
       layouttab4.setVisibility(View.INVISIBLE);
       layouttab5.setVisibility(View.INVISIBLE);
       layouttab6.setVisibility(View.INVISIBLE);
       layouttab7.setVisibility(View.INVISIBLE);
       layouttab8.setVisibility(View.INVISIBLE);

        for (int i=0;i<Home.TaskList.size();i++){
            if(i==0){
                tab1.setText(Home.TaskList.get(0));
            }else if(i==1){
                tab2.setText(Home.TaskList.get(1));
            }else if(i==2){
                tab3.setText(Home.TaskList.get(2));
                layouttab3.setVisibility(View.VISIBLE);
            }else if(i==3){
                tab4.setText(Home.TaskList.get(3));
                layouttab4.setVisibility(View.VISIBLE);
            }else if(i==4){
                tab5.setText(Home.TaskList.get(4));
                layouttab5.setVisibility(View.VISIBLE);
            }else if(i==5){
                tab6.setText(Home.TaskList.get(5));
                layouttab6.setVisibility(View.VISIBLE);
            }else if(i==6){
                tab7.setText(Home.TaskList.get(6));
                layouttab7.setVisibility(View.VISIBLE);
            }else if(i==7){
                tab8.setText(Home.TaskList.get(7));
                layouttab8.setVisibility(View.VISIBLE);
            }

        }
    }
    //delete button
    void deletelistButon(){
       TextView deltab3=findViewById(R.id.deltab3);
        deltab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(2,0);

            }
        });
       TextView deltab4=findViewById(R.id.deltab4);
        deltab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(3,0);

            }
        });
        TextView deltab5=findViewById(R.id.deltab5);
        deltab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(4,0);

            }
        });
        TextView deltab6=findViewById(R.id.deltab6);
        deltab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(5,0);
            }
        });
        TextView deltab7=findViewById(R.id.deltab7);
        deltab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(6,0);
            }
        });
        TextView deltab8=findViewById(R.id.deltab8);
        deltab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList(7,0);

            }
        });
    }


    //check list is private or not
    void checkList(int i,int chdt){
         if(i==2){
           if(saveData.LoadTab4().equals("Enable")){
               passwordCheck(i,chdt);
           }else {
               if(chdt==0){
                   Alart(i);
               }else{
                   editTaskdetails(i);
               }

           }
        }else if(i==3){
             if(saveData.LoadTab5().equals("Enable")){
                 passwordCheck(i,chdt);
             }else {
                 if(chdt==0){
                     Alart(i);
                 }else{
                     editTaskdetails(i);
                 }
             }
        }else if(i==4){
             if(saveData.LoadTab6().equals("Enable")){
                 passwordCheck(i,chdt);
             }else {
                 if(chdt==0){
                     Alart(i);
                 }else{
                     editTaskdetails(i);
                 }
             }
        }else if(i==5){
             if(saveData.LoadTab7().equals("Enable")){
                 passwordCheck(i,chdt);
             }else {
                 if(chdt==0){
                     Alart(i);
                 }else{
                     editTaskdetails(i);
                 }
             }
        }else if(i==6){
             if(saveData.LoadTab8().equals("Enable")){
                 passwordCheck(i,chdt);
             }else {
                 if(chdt==0){
                     Alart(i);
                 }else{
                     editTaskdetails(i);
                 }
             }
        }
    }

    // edit task name
    void editTaskdetails(int tname){
        Intent intent=new Intent(getApplicationContext(),AddTaskList.class);
        intent.putExtra("name",Home.TaskList.get(tname));
        intent.putExtra("stt",tname);
        intent.putExtra("title","Rename List");
        startActivity(intent);
    }

    //delsete ====================list
    void deleteList(int position){
        String slistname=Home.TaskList.get(position);
        String[]SelectionArgs={"%"+slistname+"%"};
        // ContentValues values=new ContentValues();
        Cursor cursor=dbManager.query(null,"listview LIke ? ",SelectionArgs, DBManager.listview);
        if(cursor.moveToFirst()) {
            do{
                String[] SelectionArgss={String.valueOf(slistname)};
                int count = dbManager.Delet("listview=?", SelectionArgss);
                if (count > 0) {
                    list();
                }
            } while (cursor.moveToNext());

        }

        if(position==2) {
            saveData.SaveTab4(saveData.LoadTab5());
            saveData.SavTab5(saveData.LoadTab6());
            saveData.SavTab6(saveData.LoadTab7());
            saveData.SavTab7(saveData.LoadTab8());
        }else if(position==3){
            saveData.SavTab5(saveData.LoadTab6());
            saveData.SavTab6(saveData.LoadTab7());
            saveData.SavTab7(saveData.LoadTab8());
        }else if(position==4){
            saveData.SavTab6(saveData.LoadTab7());
            saveData.SavTab7(saveData.LoadTab8());
        }else if(position==5){
            saveData.SavTab7(saveData.LoadTab8());
        }
        Home.TaskList.remove(position);
        saveArray();
    }


    public AlertDialog.Builder Alart(final int position) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(this);
        mBuilder.setTitle("Alart")
                .setMessage("Task List will be deleted.")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      deleteList(position);
                      list();
                        Home.loadpermision = 1;
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mBuilder.create();
        mBuilder.show();
        return mBuilder;


    }

    //password check alartDialog
    public void passwordCheck(final int position, final int chdt){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(RenameTask.this);
        View mView=getLayoutInflater().inflate(R.layout.verify,null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        final EditText mEmail=(EditText)mView.findViewById(R.id.pass);
        Button mLogin=(Button)mView.findViewById(R.id.chckPassword);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chdt==0) {
                    if (saveData.LoadPrivateTab().equals(mEmail.getText().toString())) {
                        Alart(position);
                        dialog.hide();
                    } else {
                        dialog.hide();
                        againpasswordCheck(position,chdt);
                    }
                }else {
                    if (saveData.LoadPrivateTab().equals(mEmail.getText().toString())) {
                        dialog.hide();
                        editTaskdetails(position);
                    } else {
                        dialog.hide();
                        againpasswordCheck(position,chdt);
                    }

                }
            }
        });


    }
    //password check alartDialog
    public void againpasswordCheck(final int position, final int chdt){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(RenameTask.this);
        View mView=getLayoutInflater().inflate(R.layout.incorectpasswordatempt,null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        final EditText mEmail=(EditText)mView.findViewById(R.id.wrpass);
        Button mLogin=(Button)mView.findViewById(R.id.wrchckPassword);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chdt==0) {
                    if (saveData.LoadPrivateTab().equals(mEmail.getText().toString())) {
                        Alart(position);
                        dialog.hide();
                    }else{
                        mEmail.setText("");
                        mEmail.setHint("Wrong Password");
                    }
                }else {
                    if (saveData.LoadPrivateTab().equals(mEmail.getText().toString())) {
                        dialog.hide();
                        editTaskdetails(position);
                    }else{
                        mEmail.setText("");
                        mEmail.setHint("Wrong Password");
                    }
                }

            }
        });


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
