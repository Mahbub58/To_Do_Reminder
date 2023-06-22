package to_do_reminder.EspritSoft.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import to_do_reminder.EspritSoft.Adaptor.ViewPagerAdaptor;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.Tab.deletedTask.DeletedTaskPrivate;

public class DeletedTask extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted_task);

        toolbar();
        viewpagerTabeLayout();
    }
    //============= Tollbar ==============================================
    void toolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        TextView hname=findViewById(R.id.hname);
        hname.setText("Task Deleted");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.navigation);
        navigationView.setCheckedItem(R.id.delete);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.taskList:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent4=new Intent(getApplicationContext(),Home.class);
                                startActivity(intent4);
                                overridePendingTransition(R.anim.slide_in_right_out,R.anim.slide_in_left);
                                finish();
                            }
                        },200);
                        break;
                    case R.id.taskFnished:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(getApplicationContext(),TaskFinished.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right_out,R.anim.slide_in_left);
                                finish();
                            }
                        },200);

                        break;
                    case R.id.taskcencle:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent2=new Intent(getApplicationContext(),CancledTask.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_in_right_out,R.anim.slide_in_left);
                                finish();
                            }
                        },200);
                        break;
                    case R.id.delete:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.setting:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1=new Intent(getApplicationContext(),setings.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.slid_in_botom,R.anim.defuly);
                            }
                        },200);
                        break;
                    case R.id.About:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent3=new Intent(getApplicationContext(),About.class);
                                startActivity(intent3);
                                overridePendingTransition(R.anim.slid_in_botom,R.anim.defuly);
                            }
                        },200);
                        break;
                }

                return false;
            }
        });

    }
    //toolbarMenu
    public static String sText="";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.tolbar_menu,menu);

        MenuItem searchItem =menu.findItem(R.id.searchbar);
        MenuItem refresh=menu.findItem(R.id.Refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sText="";
                 to_do_reminder.EspritSoft.Tab.deletedTask.DeletedTask.LoadElement();
                 DeletedTaskPrivate.LoadElement();
                return false;
            }
        });
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sText=newText;

                to_do_reminder.EspritSoft.Tab.deletedTask.DeletedTask.LoadElement();
                DeletedTaskPrivate.LoadElement();
                return false;
            }
        });
        return true;
    }


    void viewpagerTabeLayout(){

        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tablayout);

        ViewPagerAdaptor adaptor=new ViewPagerAdaptor(getSupportFragmentManager());
        adaptor.addFragment(new to_do_reminder.EspritSoft.Tab.deletedTask.DeletedTask(), "All Task");
        adaptor.addFragment(new DeletedTaskPrivate(), "Private Task");


        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);


    }
    //======================= Backprease ===============
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    //========================== Focuse Chenge =====================

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        to_do_reminder.EspritSoft.Tab.deletedTask.DeletedTask.LoadElement();
        DeletedTaskPrivate.LoadElement();
        super.onWindowFocusChanged(hasFocus);
    }
}
