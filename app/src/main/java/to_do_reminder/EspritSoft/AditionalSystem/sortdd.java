package to_do_reminder.EspritSoft.AditionalSystem;

import java.util.Comparator;

import to_do_reminder.EspritSoft.Adaptor.customItem;

public class sortdd implements Comparator<customItem> {

    @Override
    public int compare(customItem e1, customItem e2) {
        if(Integer.parseInt(e1.dd) > Integer.parseInt(e2.dd)){
            return 1;
        } else {
            return -1;
        }
    }
}