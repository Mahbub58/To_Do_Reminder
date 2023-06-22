package to_do_reminder.EspritSoft.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.customSetingItem;
import to_do_reminder.EspritSoft.Adaptor.setingItemView;
import to_do_reminder.EspritSoft.AditionalSystem.RefreshActivity;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.saveData.saveSeting;
import to_do_reminder.EspritSoft.service.CheckTotalTask;
import to_do_reminder.EspritSoft.service.Task_scheduler;

public class setings extends AppCompatActivity {
setingItemView adaptor;
    List<customSetingItem>Settingitem;
    static Context context;
    RecyclerView recyclerView;
    saveData SaveData;
    saveSeting SaveSeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context=this;
        SaveData=new saveData(this);
        SaveSeting=new saveSeting(this);

        recyclerView=findViewById(R.id.setingitem);
        //recyclerView.addItemDecoration(new DividerItemDecoration(context,10));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        settingItemList();
       backbutton();
       AdaptarClick();
    }
    void settingItemList(){
        Settingitem=new ArrayList<>();
        Settingitem.add(new customSetingItem("Edit List","Delete,Rename,Private mod Enable or Disable"));
        Settingitem.add(new customSetingItem("Password Change","Set a New Password"));
       // Settingitem.add(new customSetingItem("Troubleshooting","Solve Unexpected Error"));
        Settingitem.add(new customSetingItem("Status bar","Enable"));
        adaptor=new setingItemView(context,Settingitem,recyclerView);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }
    void AdaptarClick(){
        if(adaptor != null){
            adaptor.setOnItemClickListner(new setingItemView.OnItemClickListner() {
                @Override
                public void item(int position) {
                   switch (position){
                       case 0:
                           Intent intent=new Intent(setings.this,RenameTask.class);
                           startActivity(intent);
                           AdaptarClick();
                           break;
                       case 1:
                           passChengeDialog();
                           AdaptarClick();
                           break;
//                       case 2:
//                           Intent intent2=new Intent(setings.this,ErrorSolve.class);
//                           startActivity(intent2);
//                           AdaptarClick();
//                           break;
                       case 2:
                           if(saveSeting.statusbarLoad()==0){
                               saveSeting.statusBarSave(1);
                               Intent setr=new Intent(context, Task_scheduler.class);
                               startService(setr);
                           }else{
                               saveSeting.statusBarSave(0);
                               Intent stop=new Intent(context, Task_scheduler.class);
                               stopService(stop);
                               Intent setr=new Intent(context, Task_scheduler.class);
                               startService(setr);
                           }
                          // RefreshActivity.recreatActivity((AppCompatActivity)context);
                           settingItemList();

                           AdaptarClick();
                           break;

                   }
                }

            });
        }
    }


    void passChengeDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(setings.this);
        View view= getLayoutInflater().inflate(R.layout.setpassword,null);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        final EditText oldpass=view.findViewById(R.id.oldpass);
        final EditText newpass=view.findViewById(R.id.newpass);
        final EditText conpass=view.findViewById(R.id.confpass);
        Button submit=view.findViewById(R.id.Passwordsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveData.LoadPrivateTab().equals(oldpass.getText().toString())){
                    if(newpass.getText().toString().equals(conpass.getText().toString())){
                        saveData.SaveprivateTab(newpass.getText().toString());
                        dialog.hide();
                    }else{
                        newpass.setText("");
                        newpass.setHintTextColor(getResources().getColor(R.color.red));
                        newpass.setHint("Password Not Match");
                        conpass.setText("");
                        conpass.setHintTextColor(getResources().getColor(R.color.red));
                        conpass.setHint("Password Not Match");
                    }
                }else{
                    oldpass.setText("");
                    oldpass.setHintTextColor(getResources().getColor(R.color.red));
                    oldpass.setHint("Wrong Password");
                }
            }
        });
    }



    void backbutton(){
        ImageButton backButton=findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.defuly,R.anim.slid_out_botom);
    }
}
