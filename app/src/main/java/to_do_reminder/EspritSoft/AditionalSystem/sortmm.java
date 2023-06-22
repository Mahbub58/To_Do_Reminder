package to_do_reminder.EspritSoft.AditionalSystem;

import java.util.Comparator;

import to_do_reminder.EspritSoft.Adaptor.customItem;

public class sortmm implements Comparator<customItem> {

    @Override
    public int compare(customItem e1, customItem e2) {
        if(Integer.parseInt(e1.mm) < Integer.parseInt(e2.mm)){
            return 1;
        } else {
            return -1;
        }
    }
}