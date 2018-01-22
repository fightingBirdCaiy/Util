package util.learn.caiy.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/19.
 */

public class SplashActivity extends Activity{

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        notifySplashComplete();
    }

    /**
     * 开屏页的逻辑都完成了，通知首页预加载完成并且关闭当前页面
     */
    private void notifySplashComplete() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.isPreloading = false;
                overridePendingTransition(0,0);
                finish();
            }
        },3000);
    }

    private void initView() {

    }
}
