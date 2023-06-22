package to_do_reminder.EspritSoft.AditionalSystem;

import java.util.Comparator;

import to_do_reminder.EspritSoft.Adaptor.customItem;

public class sorthhlarg implements Comparator<customItem> {

    @Override
    public int compare(customItem e1, customItem e2) {
        if(Integer.parseInt(e1.hh) < Integer.parseInt(e2.hh)){
            return -1;
        } else {
            return 1;
        }
    }
}
