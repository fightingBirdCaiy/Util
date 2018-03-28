package util.learn.caiy.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import util.learn.caiy.com.adapter.IndicatorAdapter;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.view.CustomIndicatorView;

/**
 * Created by yongc on 2018/3/28.
 */

public class IndicatorActivity extends Activity {

    private CustomIndicatorView mIndicatorView;
    private ViewPager mViewPager;
    private IndicatorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        initView();
    }

    private void initView() {
        mIndicatorView = (CustomIndicatorView)findViewById(R.id.indicator_v);
        mViewPager = (ViewPager)findViewById(R.id.viewpager_v);
        mAdapter = new IndicatorAdapter(this,10);
        mViewPager.setAdapter(mAdapter);
        mIndicatorView.setViewPager(mViewPager,mAdapter.getCount());
    }
}
