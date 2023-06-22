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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTaskCanceled;
import to_do_reminder.EspritSoft.Adaptor.CustomAdaptorAllTaskFinish;
import to_do_reminder.EspritSoft.Adaptor.customItem;
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

import static to_do_reminder.EspritSoft.activity.Home.dbManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlltaskCencle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlltaskCencle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlltaskCencle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlltaskCencle.
     */
    // TODO: Rename and change types and number of parameters
    public static AlltaskCencle newInstance(String param1, String param2) {
        AlltaskCencle fragment = new AlltaskCencle();
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
    static CustomAdaptorAllTaskCanceled taskCanceledadaptor;
    static RecyclerView reciclerview;

    public static Context context;
    public static List<customItem> taskcancle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_alltask_cencle, container, false);

        context = getContext();
        taskcancle=new ArrayList<>();
        main();

        return view;
    }

    void main() {

        reciclerview = view.findViewById(R.id.recyclerView);



        LoadElement();

    }
    public static   void LoadElement(){

        //sarch
        String[]SelectionArgs={"%"+ CancledTask.sText+"%"};




        //clear after load
        taskcancle.clear();

        //String[] projection=("UserName","password");            //sarch
        Cursor cursor=dbManager.query(null,"task_name LIke ? ",SelectionArgs, DBManager.task_name);

        if(cursor.moveToFirst()){
            String tableData="";
            do{
            /*    tableData+=cursor.getString(cursor.getColumnIndex(DBManager.task_name))+","+
                       cursor.getString(cursor.getColumnIndex(DBManager.task_desc))+"::";
*/
                //adaptor
                if(cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("canceled")
                        || cursor.getString(cursor.getColumnIndex(DBManager.finished)).equals("canceledPrivate")) {
                    taskcancle.add(new customItem(cursor.getString(cursor.getColumnIndex(DBManager.ColID))
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
        if(taskcancle.size() >0) {
            Collections.sort(taskcancle, new sortminlarg());
            Collections.sort(taskcancle, new sorthhlarg());
            Collections.sort(taskcancle, new sortddlarg());
            Collections.sort(taskcancle, new sortmmlarg());
            Collections.sort(taskcancle, new sortyylarg());
        }

        taskCanceledadaptor = new CustomAdaptorAllTaskCanceled(context, taskcancle,reciclerview);
        reciclerview.setAdapter(taskCanceledadaptor);
        reciclerview.setLayoutManager(new LinearLayoutManager(context));
        reciclerview.addItemDecoration(new DividerItemDecoration(reciclerview.getContext(), DividerItemDecoration.VERTICAL));
        loadAdaptor();
    }



    public static  void loadAdaptor() {
        if (taskCanceledadaptor != null) {
            taskCanceledadaptor.setOnItemClickListner(new CustomAdaptorAllTaskCanceled.OnItemClickListner() {
                @Override
                public void delete(int position) {
                    Alart(position);
                }

                @Override
                public void update(int position) {
                    Intent intent = new Intent(context, NewTask.class);
                    intent.putExtra("check", 1);
                    intent.putExtra("name", taskcancle.get(position).task_name);
                    intent.putExtra("dsc", taskcancle.get(position).task_desc);
                    intent.putExtra("yy", taskcancle.get(position).yy);
                    intent.putExtra("mm", taskcancle.get(position).mm);
                    intent.putExtra("dd", taskcancle.get(position).dd);
                    intent.putExtra("hh", taskcancle.get(position).hh);
                    intent.putExtra("min", taskcancle.get(position).min);
                    intent.putExtra("id",taskcancle.get(position).ID);
                    context.startActivity(intent);
                }

                @Override
                public void complete(final int position) {

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
                        String[] SelectionArgs = {taskcancle.get(position).ID};
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
