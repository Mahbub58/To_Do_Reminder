package to_do_reminder.EspritSoft.Adaptor;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.saveData.saveData;


public  class CustomAdaptorAllTab extends RecyclerView.Adapter<CustomAdaptorAllTab.NewsViewHolder> {



    Context mContext;
    List<customItem> mDatalist;
    private OnItemClickListner mListner;
    saveData SaveData;
    public  RecyclerView recyclerView;
    public  final int UNSELECTED = -1;
    public  int selectedItem = UNSELECTED;




    public interface OnItemClickListner{
        void delete(int position);
        void update(int position);
        void complete(int position);
        void cancel(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        mListner= (OnItemClickListner) listner;
    }

    public CustomAdaptorAllTab(Context mContext, List<customItem> mData, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mDatalist = mData;
        this.recyclerView = recyclerView;

    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layout;
        layout= LayoutInflater.from(mContext).inflate(R.layout.item_view,viewGroup,false);

        return new NewsViewHolder(layout);
    }
    String y=new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
    String m=new SimpleDateFormat("M", Locale.getDefault()).format(new Date());
    String d=new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
    String h=new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
    String mi=new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {
        //bind data heare
        SaveData=new saveData(mContext);
        boolean isSelected = position == selectedItem;

          holder.id.setText(mDatalist.get(position).ID);
          holder.task_name.setText(mDatalist.get(position).task_name);
          holder.task_dsc.setText(mDatalist.get(position).task_desc);
          holder.time.setText(mDatalist.get(position).hh + ":" + mDatalist.get(position).min);
          holder.repet.setText(mDatalist.get(position).repet);
          holder.notify.setText(mDatalist.get(position).listview);

      //date
        if(Integer.parseInt(mDatalist.get(position).mm)==1){
            holder.date.setText("Jan" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==2){
            holder.date.setText("Feb" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==3){
            holder.date.setText("Mar" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==4){
            holder.date.setText("Apr" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==5){
            holder.date.setText("May" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==6){
            holder.date.setText("Jun" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==7){
            holder.date.setText("jul" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==8){
            holder.date.setText("Aug" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==9){
            holder.date.setText("Sep" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==10){
            holder.date.setText("Oct" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==11){
            holder.date.setText("Nov" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }else if(Integer.parseInt(mDatalist.get(position).mm)==12){
            holder.date.setText("Dec" +" "+ mDatalist.get(position).dd+","+mDatalist.get(position).yy);
        }

        //time
        if (DateFormat.is24HourFormat(mContext)) {
            holder.am_pm.setVisibility(View.INVISIBLE);
        }else{
            if (Integer.parseInt(mDatalist.get(position).hh) == 0) {
                holder.time.setText("12" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("AM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 12) {
                holder.time.setText("12" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 13) {
                holder.time.setText("1" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 14) {
                holder.time.setText("2" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 15) {
                holder.time.setText("3" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 16) {
                holder.time.setText("4" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 17) {
                holder.time.setText("5" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 18) {
                holder.time.setText("6" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 19) {
                holder.time.setText("7" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 20) {
                holder.time.setText("8" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 21) {
                holder.time.setText("9" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 22) {
                holder.time.setText("10" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            } else if (Integer.parseInt(mDatalist.get(position).hh) == 23) {
                holder.time.setText("11" + ":" + mDatalist.get(position).min);
                holder.am_pm.setText("PM");
            }
        }

        //ExpandLayout
        holder.item.setSelected(isSelected);
        holder.expandableLayout.setExpanded(isSelected, false);
        //===========set time and date color and check date and time ====
        if (Integer.parseInt(mDatalist.get(position).yy) > Integer.parseInt(y)) {
            holder.date.setTextColor(mContext.getResources().getColor(R.color.black));
        } else if (Integer.parseInt(mDatalist.get(position).yy) == Integer.parseInt(y)
                && Integer.parseInt(mDatalist.get(position).mm) > Integer.parseInt(m)) {
            holder.date.setTextColor(mContext.getResources().getColor(R.color.black));
        } else if (Integer.parseInt(mDatalist.get(position).yy) == Integer.parseInt(y)
                && Integer.parseInt(mDatalist.get(position).mm) == Integer.parseInt(m)
                && Integer.parseInt(mDatalist.get(position).dd) > Integer.parseInt(d)) {
            holder.date.setTextColor(mContext.getResources().getColor(R.color.blue));
        } else if (Integer.parseInt(mDatalist.get(position).yy) == Integer.parseInt(y)
                && Integer.parseInt(mDatalist.get(position).mm) == Integer.parseInt(m)
                && Integer.parseInt(mDatalist.get(position).dd) == Integer.parseInt(d)) {
            //time
            if (Integer.parseInt(mDatalist.get(position).hh) > Integer.parseInt(h)) {
                holder.date.setTextColor(mContext.getResources().getColor(R.color.green));
            } else if (Integer.parseInt(mDatalist.get(position).hh) == Integer.parseInt(h)
                    && Integer.parseInt(mDatalist.get(position).min) > Integer.parseInt(mi)) {
                holder.date.setTextColor(mContext.getResources().getColor(R.color.green));
            } else {
                holder.date.setTextColor(mContext.getResources().getColor(R.color.orange));
            }
        } else {
            holder.date.setTextColor(mContext.getResources().getColor(R.color.red));
        }


    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements ExpandableLayout.OnExpansionUpdateListener, View.OnClickListener {


        TextView task_name,task_dsc,id,date,time,repet,notify,am_pm;
        ImageView Album_Cover;
        ImageButton songListMenu;
        ImageButton delete,update,cancel;
        ExpandableLayout expandableLayout;
        RelativeLayout item;
        CheckBox completetask;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);



            item=itemView.findViewById(R.id.item);
           task_name=itemView.findViewById(R.id.tvuser);
           task_dsc=itemView.findViewById(R.id.tvpass);
            completetask=itemView.findViewById(R.id.completetask);
           date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            repet=itemView.findViewById(R.id.repet);
            notify=itemView.findViewById(R.id.ntfy);
           id=itemView.findViewById(R.id.tvid);
           delete=itemView.findViewById(R.id.budelet);
           update=itemView.findViewById(R.id.buUpdate);
           am_pm=itemView.findViewById(R.id.am_pm);
           cancel=itemView.findViewById(R.id.cancel);


            //expandable Layout
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            expandableLayout.setOnExpansionUpdateListener(this);
            item.setOnClickListener(this);

           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(mListner!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListner.delete(position);
                        }
                    }

               }
           });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListner.update(position);
                        }
                    }
                }
            });
            completetask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListner.complete(position);
                        }
                    }
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListner.cancel(position);
                        }
                    }
                }
            });

        }
        //ExpandLayout
        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            if (state == ExpandableLayout.State.EXPANDING) {
                recyclerView.smoothScrollToPosition(getAdapterPosition());

            }
        }
        //ExpandLayout
        @Override
        public void onClick(View v) {
            NewsViewHolder holder = (NewsViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null ) {
                holder.item.setSelected(false);
                holder.expandableLayout.collapse();
              //  expent=0;
            }

            int position = getAdapterPosition();
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                item.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }
        }
    }
}
