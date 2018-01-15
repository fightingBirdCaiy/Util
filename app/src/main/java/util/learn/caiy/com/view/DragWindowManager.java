package util.learn.caiy.com.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2018/1/8.
 */

public class DragWindowManager {

    private static final String TAG = "DragWindowManager";

    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams wmParams;

    private DragViewInWindowManager mFloatLayout;

    private int statusBarHeight;

    public DragWindowManager(Context context) {
        if (null == context) {
            return;
        }
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        // 设置window type; 19以下的机型设置TYPE_PHONE才能拖动画中画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        } else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        // 设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.TRANSPARENT;
        // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | wmParams.flags;
        // 调整悬浮窗显示的停靠位置为左上角
        wmParams.gravity = Gravity.START | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 0;
//        wmParams.y = 0;
        wmParams.y = getTopMargin();
        // 设置悬浮窗口长宽数据 wmParams.width = 200; wmParams.height = 80;
        // 设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // 获取浮动窗口视图所在布局
        mFloatLayout = new DragViewInWindowManager(mContext);
        mFloatLayout.setDragManager(this);
        initView();
    }

    private void initView() {
        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        if (mWindowManager != null && mFloatLayout != null && wmParams != null) {
            if (null == mFloatLayout.getParent()) {
                mWindowManager.addView(mFloatLayout, wmParams);
            }
        }
    }

    /**
     * 更新悬浮窗在屏幕中的位置。
     */
    public void updateViewPosition(int x, int y) {
        wmParams.x = x;
        wmParams.y = y;
        if (null != mFloatLayout.getContext() && null != mFloatLayout.getParent()) {
            mWindowManager.updateViewLayout(mFloatLayout, wmParams);
        }
    }

    public void resetViewPosition(final float originX,final float originY) {
        final float targetX = 0f;
        final float targetY = originY;

        ValueAnimator animator = ValueAnimator.ofFloat(1000f,0f);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentAnimatedValue = (float) animation.getAnimatedValue();
                float ration = currentAnimatedValue/1000f;
                float currentX = targetX + (originX - targetX)*ration;
                float currentY = targetY;
                Log.i(TAG,String.format("ration=%f,currentX=%f,currentY=%f",currentAnimatedValue,currentX,currentY));

                if (null != mFloatLayout.getContext() && null != mFloatLayout.getParent()) {
                    wmParams.x = (int)currentX;
                    wmParams.y = (int)currentY;
                    mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                }
            }
        });
        animator.start();
    }

    private int getTopMargin() {
        int height = ToolUtil.getHeight(mContext);
        float margin = height * (1-0.818f);
        return (int)margin;
    }
}
