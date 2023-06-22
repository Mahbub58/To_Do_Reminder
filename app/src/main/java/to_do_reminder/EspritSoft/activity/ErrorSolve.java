package to_do_reminder.EspritSoft.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import to_do_reminder.EspritSoft.R;

public class ErrorSolve extends AppCompatActivity {
static Context context;
TextView texterr;
Button svbutton;
Toolbar toolbar;
TextView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_solve);
        context=this;

        texterr=findViewById(R.id.texterr);
        svbutton=findViewById(R.id.solvButton);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.hname);
        home.setText("Troubleshooting");
        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (Build.VERSION.SDK_INT < 23) {
            texterr.setText("We could not find anny problem !\n\t (Thank you)");
            //svbutton.setEnabled(false);
            svbutton.setVisibility(View.INVISIBLE);
        }

    }

    public void start(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            Intent intent = new Intent();
         //   String packageName = context.getPackageName();
          //  PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                     intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                      startActivity(intent);


        }
    }
}
