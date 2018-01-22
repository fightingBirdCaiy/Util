package util.learn.caiy.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/22.
 */

/**
 * 冷启动预加载的一种实现方案(StartActivity启动HomeActivity,HomeActivity启动SplashActivity,达到HomeActivity的预加载)
 */
public class StartActivity extends Activity{

    /**
     * 是否已经启动HomeActivity
     */
    private boolean hasStartHomeActivity = false;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(StartActivity.this,HomeActivity.class);
            overridePendingTransition(0,0);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        HomeActivity.isPreloading = true;

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(hasStartHomeActivity){
            return;
        }
        hasStartHomeActivity = true;
        mHandler.sendEmptyMessageDelayed(100, 1000);
    }

    private void initView() {
    }
}
