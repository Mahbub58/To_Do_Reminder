package to_do_reminder.EspritSoft.Adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customItem {

    public String ID;
    public String task_name;
    public String task_desc;
    public String yy;
    public String mm;
    public String dd;
    public String hh;
    public String min;
    public String ss;
    public String repet;
    public String ntfy;
    public String finished;
    public String ntfyState;
    public String listview;
    //for news details


    public customItem(String ID, String task_name, String task_desc, String yy, String mm, String dd, String hh, String min,String ss, String repet, String ntfy
    ,String finished,String ntfyState,String listview) {
        this.ID = ID;
        this.task_name = task_name;
        this.task_desc = task_desc;
        this.yy = yy;
        this.mm = mm;
        this.dd = dd;
        this.hh = hh;
        this.min = min;
        this.ss=ss;
        this.repet = repet;
        this.ntfy = ntfy;
        this.finished=finished;
        this.ntfyState=ntfyState;
        this.listview=listview;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
