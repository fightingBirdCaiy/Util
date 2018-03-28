package util.learn.caiy.com.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2018/3/28.
 */

public class CustomIndicatorView extends HorizontalScrollView{

    private static final String TAG = "CustomIndicatorView";

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private ViewPager mViewPager;

    private int childCount;

    private LinearLayout mContentRootLayout;

    private int mSelectedPosition = 0;
    private int mCurrentPosition = 0;
    private float mCurrentPositionOffset = 0F;

    public CustomIndicatorView(Context context) {
        this(context,null);
    }

    public CustomIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mContentRootLayout = new LinearLayout(context);
        mContentRootLayout.setOrientation(LinearLayout.HORIZONTAL);
        mContentRootLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        addView(mContentRootLayout);
    }


    public void setViewPager(ViewPager viewPager,int count){

        mViewPager = viewPager;
        childCount = count;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG,"onPageScrolled:position=" + position + ",positionOffset=" + positionOffset);
                mCurrentPosition = position;
                mCurrentPositionOffset = positionOffset;
                updateViewOnPageScrolled(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG,"onPageSelected:position=" + position);
                mSelectedPosition = position;
                updateViewOnPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int i=0;i<childCount;i++){
            ViewGroup itemView = (ViewGroup) mLayoutInflater.inflate(R.layout.item_indicator,this,false);
            final int k = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(k);
                }
            });
            TextView itemTextView = (TextView)itemView.findViewById(R.id.content_tv);
            int j = i+1;
            itemTextView.setText("导航条" + j);
            mContentRootLayout.addView(itemView);
        }

//        mViewPager.setCurrentItem(0);
        updateViewOnPageSelected(0);
    }

    private void updateViewOnPageScrolled(int position, float positionOffset) {
        if(position < mSelectedPosition){//从右往左滑动
            ViewGroup leftTab = (ViewGroup) mContentRootLayout.getChildAt(mSelectedPosition-1);
            ViewGroup currentTab = (ViewGroup) mContentRootLayout.getChildAt(mSelectedPosition);
            updateTabContentView(leftTab,1-positionOffset);
            updateTabContentView(currentTab,positionOffset);
        }else{//从左往右滑动
            ViewGroup currentTab = (ViewGroup) mContentRootLayout.getChildAt(mSelectedPosition);
            ViewGroup rightTab = (ViewGroup) mContentRootLayout.getChildAt(mSelectedPosition + 1);
            updateTabContentView(currentTab,1-positionOffset);
            updateTabContentView(rightTab,positionOffset);
        }
    }

    private void updateTabContentView(View view, float positionOffset) {
        ViewGroup itemView = (ViewGroup)view;
        TextView itemTextView = (TextView)itemView.findViewById(R.id.content_tv);

//        itemTextView.setTextSize(36+12F*positionOffset);
        itemTextView.setScaleX(1+1F*positionOffset);
        itemTextView.setScaleY(1+1F*positionOffset);
    }

    private void updateViewOnPageSelected(int selectedPosition) {
        int count = mContentRootLayout.getChildCount();
        for(int i=0;i<count;i++){
            if(i == selectedPosition){
                updateSelectedView(mContentRootLayout.getChildAt(i));
            }else{
                updateUnSelectedView(mContentRootLayout.getChildAt(i));
            }
        }
    }

    private void updateUnSelectedView(View view) {
        ViewGroup itemView = (ViewGroup)view;
        TextView itemTextView = (TextView)itemView.findViewById(R.id.content_tv);
        itemTextView.setTextColor(Color.parseColor("#000000"));

        updateTabContentView(view,0);
    }

    private void updateSelectedView(View view) {
        ViewGroup itemView = (ViewGroup)view;
        TextView itemTextView = (TextView)itemView.findViewById(R.id.content_tv);
        itemTextView.setTextColor(Color.parseColor("#ff0000"));

        updateTabContentView(view,1);
    }
}
