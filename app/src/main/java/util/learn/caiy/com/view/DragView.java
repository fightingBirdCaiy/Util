package util.learn.caiy.com.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2018/1/3.
 */

public class DragView extends LinearLayout{

    private static final String TAG = "DragView";

    private View mContentView;

    // 记录手指按下的坐标
    private float mInitPointX;
    private float mInitPointY;

    // 记录手指上次触摸的坐标
    private float mLastPointX;
    private float mLastPointY;

    //用于识别最小的滑动距离
    private int mSlop;

    // 状态分别空闲、拖拽两种
    enum State {
        IDLE,
        DRAGGING
    }

    private State mCurrentState = State.IDLE;

    public DragView(Context context) {
        this(context,null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
//        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();//距离大，拖动会闪
        mSlop = 0;
        LayoutInflater inflater = LayoutInflater.from(context);
        setOrientation(LinearLayout.HORIZONTAL);
        mContentView = inflater.inflate(R.layout.view_drag_content,this);
        TextView mContentTextView = (TextView)mContentView.findViewById(R.id.content_tv);
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
                mInitPointX = mLastPointX = event.getRawX();//这里不能换成getX(),否则view会抖动
                mInitPointY = mLastPointY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int)(event.getRawX() - mLastPointX);
                int deltaY = (int)(event.getRawY() - mLastPointY);
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
                //onInterceptTouchEvent方法中进行了赋值操作
                Log.i(TAG,String.format("ACTION_DOWN left=%d,top=%d",getLeft(),getTop()));
                Log.i(TAG,String.format("ACTION_DOWN translationX=%f,translationY=%f",getTranslationX(),
                        getTranslationY()));
                break;
            case MotionEvent.ACTION_MOVE:
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                int deltaX = (int)(rawX - mLastPointX);
                int deltaY = (int)(rawY - mLastPointY);
                if ((Math.abs(deltaX) > mSlop || Math.abs(deltaY) > mSlop)) {
                    mCurrentState = State.DRAGGING;

                    //如果符合条件则进行位置移动
                    setTranslationX(getNextTranslationX(event));
                    setTranslationY(getNextTranslationY(event));

                    Log.i(TAG,String.format("ACTION_MOVE translationX=%f,translationY=%f",getTranslationX(),
                            getTranslationY()));
                    mLastPointX = event.getRawX();
                    mLastPointY = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if ( mCurrentState == State.DRAGGING ){
                    // 标记状态为空闲
                    mCurrentState = State.IDLE;
                    animToStartPosition();
                }
                break;
        }
        return true;
    }

    /**
     * 修正TranslationY
     * 不能超出状态栏以及手机最下方
     * @param event
     * @return
     */
    private float getNextTranslationY(MotionEvent event) {
        int top = getTop();
        float nextTranslationY = event.getRawY()-mInitPointY;
        float minY = -(top - ToolUtil.getStatusBarHeight(getContext()));
        float maxY = ToolUtil.getHeight(getContext()) - top - getHeight();
        if(nextTranslationY < minY){
            nextTranslationY = minY;
        }else if(nextTranslationY > maxY){
            nextTranslationY = maxY;
        }

        return nextTranslationY;
    }

    /**
     * 修正TranslationX
     * 不能超出手机最左边和最右边
     * @param event
     * @return
     */
    private float getNextTranslationX(MotionEvent event) {
        float nextTranslationX = event.getRawX() - mInitPointX;
        float minX = 0f;
        float maxX = ToolUtil.getWidth(getContext()) - getWidth();
        if(nextTranslationX < minX){
            nextTranslationX = minX;
        }else if(nextTranslationX > maxX){
            nextTranslationX = maxX;
        }
        return nextTranslationX;
    }

    /**
     * 平缓滑动到屏幕最左边
     */
    private void animToStartPosition() {

        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(this,"translationX", 0);

        final float mTranslationY = getTranslationY();


        final int[] location = new  int[2] ;
        getLocationOnScreen(location);
        Log.i(TAG,"animToStartPosition location=" + location[1] + ",getTop=" + getTop() + ",translationY=" +mTranslationY);

        transXAnim.setDuration(300);
        transXAnim.start();
        transXAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup.LayoutParams params = getLayoutParams();
                if(params instanceof FrameLayout.LayoutParams){
                    FrameLayout.LayoutParams frameLayoutParams = (FrameLayout.LayoutParams)params;
                    Log.i(TAG,"animToStartPosition old top margin=" + frameLayoutParams.topMargin);
                    frameLayoutParams.topMargin = location[1];
                    setLayoutParams(frameLayoutParams);
                    setTranslationY(0);
                }else{
                    Log.e(TAG,"异常，非FrameLayout.LayoutParams");
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i(TAG,"onDraw方法调用了");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        Log.i(TAG,"onLayout方法调用了");
    }
}
