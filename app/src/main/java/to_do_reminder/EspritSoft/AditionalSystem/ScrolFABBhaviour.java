package to_do_reminder.EspritSoft.AditionalSystem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import to_do_reminder.EspritSoft.activity.Home;

public class ScrolFABBhaviour extends FloatingActionButton.Behavior {


    public ScrolFABBhaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if(dy>0 && child.getVisibility()==View.VISIBLE){
           // child.hide();
            Home.invisible();
        }else if(dy<0){
            //child.show();
            Home.visible();
        }

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {

        if (type == ViewCompat.TYPE_TOUCH) {
         //   return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type) || ((axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0);
        }
       // return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type) || ((axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0);
    }
}
