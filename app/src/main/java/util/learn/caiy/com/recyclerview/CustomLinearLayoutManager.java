package util.learn.caiy.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by admin on 2017/10/10.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager{

    private boolean canCustomScrollVertical;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                     int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return canCustomScrollVertical && super.canScrollVertically();
    }

    public void setCanCustomScrollVertical(boolean canCustomScrollVertical) {
        this.canCustomScrollVertical = canCustomScrollVertical;
    }
}
