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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import to_do_reminder.EspritSoft.R;
import to_do_reminder.EspritSoft.saveData.saveData;
import to_do_reminder.EspritSoft.saveData.saveSeting;


public  class setingItemView extends RecyclerView.Adapter<setingItemView.NewsViewHolder> {



    Context mContext;
    List<customSetingItem> mDatalist;
    private OnItemClickListner mListner;
    saveData SaveData;
    saveSeting SaveSeting;
    public  RecyclerView recyclerView;
    public  final int UNSELECTED = -1;
    public  int selectedItem = UNSELECTED;




    public interface OnItemClickListner{
        void item(int position);
//        void update(int position);
//        void complete(int position);
//        void cancel(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        mListner= (OnItemClickListner) listner;
    }

    public setingItemView(Context mContext, List<customSetingItem> mData, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mDatalist = mData;
        this.recyclerView = recyclerView;

    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layout;
        layout= LayoutInflater.from(mContext).inflate(R.layout.item_view_setting,viewGroup,false);

        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {
        //bind data heare
        SaveData=new saveData(mContext);
        SaveSeting=new saveSeting(mContext);
        if(position==2 && saveSeting.statusbarLoad()==0){
            holder.setingTitle.setText(mDatalist.get(position).title);
            holder.seting_desc.setText("Disable");
        }else {
            holder.setingTitle.setText(mDatalist.get(position).title);
            holder.seting_desc.setText(mDatalist.get(position).desc);
        }





    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder  {


        TextView setingTitle, seting_desc;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);


            setingTitle = itemView.findViewById(R.id.seting_item_title);
            seting_desc = itemView.findViewById(R.id.seting_item_desc);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListner.item(position);
                        }
                    }

                }
            });
//            update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListner != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListner.update(position);
//                        }
//                    }
//                }
//            });
//            completetask.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListner != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListner.complete(position);
//                        }
//                    }
//                }
//            });
//            cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListner != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListner.cancel(position);
//                        }
//                    }
//                }
//            });

        }
    }
}
