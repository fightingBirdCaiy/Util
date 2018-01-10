package util.learn.caiy.com.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2018/1/3.
 */

public class DragViewInWindowManager extends LinearLayout{

    private static final String TAG = "DragViewInWindowManager";

    private DragWindowManager mDragManager;
    private int statusBarHeight = 0;

    private ViewGroup mContentView;

    //用于识别最小的滑动距离
    private int mSlop;

    private TextView mContentTextView;

    private Context mContext;

    private float mInitX;
    private float mInitY;
    private float mInitInScreenX;
    private float mInitInScreenY;
    private float mLastX;
    private float mLastY;

    public DragViewInWindowManager(Context context) {
        this(context,null);
    }

    public DragViewInWindowManager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragViewInWindowManager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();//距离大，拖动会闪
//        mSlop = 0;
        LayoutInflater inflater = LayoutInflater.from(context);
        setOrientation(LinearLayout.HORIZONTAL);
        mContentView = (ViewGroup) inflater.inflate(R.layout.view_drag_content,this);
        mContentTextView = (TextView)mContentView.findViewById(R.id.content_tv);
        mContentTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
            }
        });
        Log.i(TAG,String.format("init mSlop=%d",mSlop));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                mInitX = event.getX();
                mInitY = event.getY();

                mInitInScreenX = mLastX = event.getRawX();
                mInitInScreenY = mLastY = event.getRawY() - getStatusBarHeight();

                Log.i(TAG,String.format("mInitX=%f,mInitY=%f,mLastX=%f,mLastY=%f",mInitX,mInitY,mLastX,mLastY));
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int)(event.getRawX() - mInitInScreenX);
                int deltaY = (int)(event.getRawY() - mInitInScreenY);
                if ((Math.abs(deltaX) > mSlop || Math.abs(deltaY) > mSlop)) {
                    //如果大于滑动阈值，则拦截事件(当前view的onTouchEvent事件会执行)
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mLastX = event.getRawX();
                mLastY = event.getRawY() - getStatusBarHeight();
                int x = (int)(mLastX - mInitX);
                int y = (int)(mLastY - mInitY);
                mDragManager.updateViewPosition(x,y);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
                float xUpInScreen = event.getRawX();
                float yUpInScreen = event.getRawY() - getStatusBarHeight();
                if (Math.abs(xUpInScreen - mInitInScreenX) < mSlop
                        && Math.abs(yUpInScreen - mInitInScreenY) < mSlop) {
                    //do nothing
                }else{
                    float originX = xUpInScreen - mInitX;
                    float originY = yUpInScreen - mInitY;
                    mDragManager.resetViewPosition(originX,originY);
                }
                break;
        }
        return true;
    }

    public void setDragManager(DragWindowManager mDragManager) {
        this.mDragManager = mDragManager;
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            statusBarHeight = ToolUtil.getStatusBarHeight(mContext);
        }
        return statusBarHeight;
    }
}
