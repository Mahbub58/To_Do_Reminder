package to_do_reminder.EspritSoft.Tab;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTask;
import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.AditionalSystem.CheckTabeActivity;
import to_do_reminder.EspritSoft.AditionalSystem.sortdd;
import to_do_reminder.EspritSoft.AditionalSystem.sorthh;
import to_do_reminder.EspritSoft.AditionalSystem.sortmin;
import to_do_reminder.EspritSoft.AditionalSystem.sortmm;
import to_do_reminder.EspritSoft.AditionalSystem.sortyy;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.activity.NewTask;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.service.manageData;


import static to_do_reminder.EspritSoft.activity.Home.RecordID;
import static to_do_reminder.EspritSoft.activity.Home.dbManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    EditText etsearch;
    Button Add,search;
    static CustomAdaptorAllTask adaptor;
    static RecyclerView reciclerview;

    public static Context context;
    public static List<customItem> Tab1;
    public static boolean tab1IsNotNull=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        // Inflate the layout for this fragment

        context = getContext();
        Tab1=new ArrayList<>();

        main();


        return view;
    }

    void main() {

        CheckTabeActivity.tab1activityNotNull=true;

        reciclerview = view.findViewById(R.id.recyclerView);
        reciclerview.addItemDecoration(new DividerItemDecoration(reciclerview.getContext(), DividerItemDecoration.VERTICAL));


     LoadElement();
    }


   public static void LoadElement(){


        //sarch
        String[]SelectionArgs={"%"+Home.sText+"%"};





        //clear after load
        Tab1.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor=dbManager.query(null,"task_name LIke ? ",SelectionArgs, DBManager.task_name);

        if(cursor.moveToFirst()){
            String tableData="";
            do{
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/


                //adaptor
                if(cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("du")) {
                    Tab1.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
                            , cursor.getString(cursor.getColumnIndex(DBManager.task_name))
                            , cursor.getString(cursor.getColumnIndex(DBManager.task_desc))
                            , cursor.getString(cursor.getColumnIndex(DBManager.yy))
                            , cursor.getString(cursor.getColumnIndex(DBManager.mm))
                            , cursor.getString(cursor.getColumnIndex(DBManager.dd))
                            , cursor.getString(cursor.getColumnIndex(DBManager.hh))
                            , cursor.getString(cursor.getColumnIndex(DBManager.min))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ss))
                            , cursor.getString(cursor.getColumnIndex(DBManager.repet))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ntfy))
                            , cursor.getString(cursor.getColumnIndex(DBManager.finished))
                            , cursor.getString(cursor.getColumnIndex(DBManager.ntfState))
                            , cursor.getString(cursor.getColumnIndex(DBManager.listview))
                    ));
                }
            }while ((cursor.moveToNext()));
            //   Toast.makeText(,tableData,Toast.LENGTH_LONG).show();


        }
      //sorting
        if(Tab1.size() >0) {
            Collections.sort(Tab1, new sortmin());
            Collections.sort(Tab1, new sorthh());
            Collections.sort(Tab1, new sortdd());
            Collections.sort(Tab1, new sortmm());
            Collections.sort(Tab1, new sortyy());
        }


        adaptor = new CustomAdaptorAllTask(context, Tab1,reciclerview);
        reciclerview.setAdapter(adaptor);
        reciclerview.setLayoutManager(new LinearLayoutManager(context));




        loadAdaptor();
    }



    public static void loadAdaptor() {
        if (adaptor != null) {
            adaptor.setOnItemClickListner(new CustomAdaptorAllTask.OnItemClickListner() {
                @Override
                public void delete(int position) {
                    Alart(position,"Task will be deleted","Delete","Cancel",1);
                }

                @Override
                public void update(int position) {
                    Intent intent = new Intent(context, NewTask.class);
                    intent.putExtra("check", 1);
                    intent.putExtra("name", Tab1.get(position).task_name);
                    intent.putExtra("dsc", Tab1.get(position).task_desc);
                    intent.putExtra("yy", Tab1.get(position).yy);
                    intent.putExtra("mm", Tab1.get(position).mm);
                    intent.putExtra("dd", Tab1.get(position).dd);
                    intent.putExtra("hh", Tab1.get(position).hh);
                    intent.putExtra("min", Tab1.get(position).min);
                    intent.putExtra("id",Tab1.get(position).ID);
                    context.startActivity(intent);
                    // NewTask.etuser.setText(mDatalist.get(position).UserName);
                    // NewTask.etpass.setText(mDatalist.get(position).Password);


                }
                int click=0;
                @Override
                public void complete(final int position) {
                    click++;
                  new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          if(click%2!=0){
                              Home.RecordID = Tab1.get(position).ID;
                              ContentValues values=new ContentValues();
                              values.put(DBManager.finished,"complete");
                              values.put(DBManager.ColID,RecordID);
                              String[] SelectionArgs={String.valueOf(RecordID)};
                              dbManager.Update(values,"id=?",SelectionArgs);

                              LoadElement();
                          }
                      }
                  },2000);
                }

                @Override
                public void cancel(int position) {
                    Alart(position,"Are you sure Task is canceled?","Yes","No",0);
                }

            });

        }
    }

    public static AlertDialog.Builder Alart(final int position, String title, String actiontxt1,String actionyxy2, final int action) {
        androidx.appcompat.app.AlertDialog.Builder mBuilder=new AlertDialog.Builder(context);
        mBuilder.setTitle("Alart")
                .setMessage(title)
                .setPositiveButton(actiontxt1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(action==1) {  //1 for delete
                            Home.RecordID = Tab1.get(position).ID;
                            ContentValues values = new ContentValues();
                            values.put(DBManager.finished, "deleted");
                            values.put(DBManager.ColID, RecordID);
                            String[] SelectionArgs = {String.valueOf(RecordID)};
                            dbManager.Update(values, "id=?", SelectionArgs);

                            //set alarm
                            Intent serviceIntent = new Intent(context, manageData.class);
                            context.startService(serviceIntent);

                            LoadElement();
                        }else {
                            Home.RecordID = Tab1.get(position).ID;
                            ContentValues values = new ContentValues();
                            values.put(DBManager.finished, "canceled");
                            values.put(DBManager.ColID, RecordID);
                            String[] SelectionArgs = {String.valueOf(RecordID)};
                            dbManager.Update(values, "id=?", SelectionArgs);
                            //set alarm
                            Intent serviceIntent = new Intent(context, manageData.class);
                            context.startService(serviceIntent);
                            LoadElement();
                        }
                    }
                })
                .setNegativeButton(actionyxy2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mBuilder.create();
        mBuilder.show();
        return mBuilder;


    }

}





