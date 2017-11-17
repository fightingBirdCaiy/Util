package util.learn.caiy.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2017/11/17.
 * 参考文章 https://www.douban.com/note/577678148/
 */

public class ResizeRelativeLayout extends RelativeLayout{

    private static final String TAG = "ResizeRelativeLayout";

    private static int count = 0;

    public ResizeRelativeLayout(Context context) {
        super(context);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "=>onResize called! w="+w + ",h="+h+",oldw="+oldw+","
                + "oldh="+oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i(TAG, "=>OnLayout called! l=" + l + ", t=" + t + ",r=" + r + ","
                + "b="+b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "=>onMeasure called! widthMeasureSpec=" + widthMeasureSpec
                + ", heightMeasureSpec=" + heightMeasureSpec);
    }
}
