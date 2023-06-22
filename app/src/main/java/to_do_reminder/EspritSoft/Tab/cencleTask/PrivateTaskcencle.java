package to_do_reminder.EspritSoft.Tab.cencleTask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTaskCanceledPrivate;
import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTaskFinishPrivate;
import to_do_reminder.EspritSoft.Adaptor.customItem;
import to_do_reminder.EspritSoft.AditionalSystem.CheckTabeActivity;
import to_do_reminder.EspritSoft.AditionalSystem.sortddlarg;
import to_do_reminder.EspritSoft.AditionalSystem.sorthhlarg;
import to_do_reminder.EspritSoft.AditionalSystem.sortminlarg;
import to_do_reminder.EspritSoft.AditionalSystem.sortmmlarg;
import to_do_reminder.EspritSoft.AditionalSystem.sortyylarg;
import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.activity.CancledTask;
import to_do_reminder.EspritSoft.activity.Home;
import to_do_reminder.EspritSoft.activity.NewTask;
import to_do_reminder.EspritSoft.activity.TaskFinished;
import to_do_reminder.EspritSoft.saveData.DBManager;
import to_do_reminder.EspritSoft.saveData.saveData;

import static to_do_reminder.EspritSoft.activity.Home.dbManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrivateTaskcencle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivateTaskcencle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrivateTaskcencle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivateTaskcencle.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivateTaskcencle newInstance(String param1, String param2) {
        PrivateTaskcencle fragment = new PrivateTaskcencle();
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

    static CustomAdaptorAllTaskCanceledPrivate adaptorcancleTask;
    EditText pass,wrpass,newpass,cfpass,chsecuritycode,newsecuritycode;
    Button Add, passwordsubmit,checkPassword,wrcheckPassword,checkSecuritysubmit;
    TextView forgate,incorrect_code;

    static RecyclerView reciclerview;
    saveData SaveData;
    public static Context context;
    public static List<customItem> taskFinishPrivate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_private_taskcencle, container, false);
        context = getContext();
        SaveData=new saveData(context);
        taskFinishPrivate = new ArrayList<>();
        main();

        return view;
    }
    RelativeLayout verify,verify2,newSet,checksecuritycode;
    void main() {
        CheckTabeActivity.tab3activityNotNull = true;
        reciclerview = view.findViewById(R.id.recyclerView);


        verify = view.findViewById(R.id.verify);
        verify2 = view.findViewById(R.id.verify2);
        newSet = view.findViewById(R.id.newset);
        checksecuritycode = view.findViewById(R.id.checksecuritycode);
        pass = view.findViewById(R.id.pass);
        checkPassword = view.findViewById(R.id.chckPassword);
        wrpass = view.findViewById(R.id.wrpass);
        wrcheckPassword = view.findViewById(R.id.wrchckPassword);
        newpass = view.findViewById(R.id.newpass);
        cfpass = view.findViewById(R.id.confpass);
        passwordsubmit = view.findViewById(R.id.Passwordsubmit);
        forgate = view.findViewById(R.id.forgate);
        chsecuritycode = view.findViewById(R.id.chsecuritycode);
        checkSecuritysubmit = view.findViewById(R.id.checkSecuritysubmit);
        newsecuritycode = view.findViewById(R.id.newsecuritycode);
        passwordSystem();
        incorrect_code = view.findViewById(R.id.incarecetcode);

        LoadElement();

    }

    void passwordSystem() {
        //int check=saveData.chLoadtab3();
        if (saveData.chLoadtab3() == 0) {
            verify.setVisibility(View.INVISIBLE);
            newSet.setVisibility(View.VISIBLE);
            passwordsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newpass.getText().toString().isEmpty() && !newsecuritycode.getText().toString().isEmpty()) {
                        if (newpass.getText().toString().equals(cfpass.getText().toString())) {
                            saveData.SaveprivateTab(newpass.getText().toString());
                            saveData.chSavetab3(1);
                            saveData.SaveprivateTabSecurity(newsecuritycode.getText().toString());
                            newSet.setVisibility(View.INVISIBLE);
                            passwordSystem();
                        } else {
                            Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Password & security code can't be empty.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            verify.setVisibility(View.VISIBLE);
            checkPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (saveData.LoadPrivateTab().equals(pass.getText().toString())) {
                        verify.setVisibility(View.INVISIBLE);
                        reciclerview.setVisibility(View.VISIBLE);
                    } else {
                        verify.setVisibility(View.INVISIBLE);
                        verify2.setVisibility(View.VISIBLE);
                    }
                }
            });
            wrcheckPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (saveData.LoadPrivateTab().equals(wrpass.getText().toString())) {
                        verify2.setVisibility(View.INVISIBLE);
                        reciclerview.setVisibility(View.VISIBLE);
                    } else {
                        wrpass.setText("");
                        wrpass.setHint("Wrong Password");
                    }
                }
            });
        }
        forgate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify2.setVisibility(View.INVISIBLE);
                incorrect_code.setVisibility(View.INVISIBLE);
                checksecuritycode.setVisibility(View.VISIBLE);
            }
        });
        checkSecuritysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveData.LoadPrivateTabSecurity().equals(chsecuritycode.getText().toString())) {
                    checksecuritycode.setVisibility(View.INVISIBLE);
                    saveData.chSavetab3(0);
                    passwordSystem();
                } else {
                    chsecuritycode.setText("");
                    chsecuritycode.setHint("Incorrect Code");
                    incorrect_code.setVisibility(View.VISIBLE);
                }
            }
        });

    }



    public static void LoadElement(){

        //sarch
        String[]SelectionArgs={"%"+ CancledTask.sText +"%"};




        //clear after load
        taskFinishPrivate.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor=dbManager.query(null,"task_name LIke ? ",SelectionArgs, DBManager.task_name);

        if(cursor.moveToFirst()){
            String tableData="";
            do{
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/
                //adaptor
                if(cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("canceledPrivate")) {
                    taskFinishPrivate.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
        if(taskFinishPrivate.size() >0) {
            Collections.sort(taskFinishPrivate, new sortminlarg());
            Collections.sort(taskFinishPrivate, new sorthhlarg());
            Collections.sort(taskFinishPrivate, new sortddlarg());
            Collections.sort(taskFinishPrivate, new sortmmlarg());
            Collections.sort(taskFinishPrivate, new sortyylarg());
        }


        adaptorcancleTask = new CustomAdaptorAllTaskCanceledPrivate(context, taskFinishPrivate, reciclerview);
        reciclerview.setAdapter(adaptorcancleTask);
        reciclerview.setLayoutManager(new LinearLayoutManager(context));
        reciclerview.addItemDecoration(new DividerItemDecoration(reciclerview.getContext(), DividerItemDecoration.VERTICAL));
        loadAdaptor();
    }



    public static void loadAdaptor() {
        if (adaptorcancleTask != null) {
            adaptorcancleTask.setOnItemClickListner(new CustomAdaptorAllTaskCanceledPrivate.OnItemClickListner() {
                @Override
                public void delete(int position) {
                    Alart(position);
                }

                @Override
                public void update(int position) {
                    Intent intent = new Intent(context, NewTask.class);
                    intent.putExtra("check", 1);
                    intent.putExtra("name", taskFinishPrivate.get(position).task_name);
                    intent.putExtra("dsc", taskFinishPrivate.get(position).task_desc);
                    intent.putExtra("yy", taskFinishPrivate.get(position).yy);
                    intent.putExtra("mm", taskFinishPrivate.get(position).mm);
                    intent.putExtra("dd", taskFinishPrivate.get(position).dd);
                    intent.putExtra("hh", taskFinishPrivate.get(position).hh);
                    intent.putExtra("min", taskFinishPrivate.get(position).min);
                    intent.putExtra("id",taskFinishPrivate.get(position).ID);
                    context.startActivity(intent);
                }

                @Override
                public void complete(int position) {

                }

//                @Override
//                public void item(int position) {
//                    Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
//                }
            });
        }
    }
    public static AlertDialog.Builder Alart(final int position) {
        androidx.appcompat.app.AlertDialog.Builder mBuilder=new AlertDialog.Builder(context);
        mBuilder.setTitle("Alart")
                .setMessage("Task will be deleted permanently.")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] SelectionArgs = {taskFinishPrivate.get(position).ID};
                        int count = dbManager.Delet("ID=?", SelectionArgs);
                        //refresh Element
                        if (count > 0) {
                            LoadElement();
                        }
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


}