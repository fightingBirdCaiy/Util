package util.learn.caiy.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/19.
 */

public class HomeActivity extends Activity{

    /**
     * 是否正在预加载
     * true 正在预加载
     * false 不是预加载
     */
    public static boolean isPreloading = true;

    private static final int WHAT = 100;

    private long count = 1;
    private TextView mHintTextView;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            mHintTextView.setText("首页HomeActivity" + count);
            count++;
            mHandler.sendEmptyMessageDelayed(WHAT,1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        if(HomeActivity.isPreloading) {
            startPreloadActivity();
            doSomethingWhenPreload();
        }

    }

    /**
     * 这里可以做一些预加载的事情：例如请求api数据然后更新ui等
     * (例子中只是定时改变ui，表示预加载了首页)
     */
    private void doSomethingWhenPreload() {
        mHandler.sendEmptyMessage(WHAT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(HomeActivity.isPreloading) {
            return;//如果是预加载，直接返回，不走业务逻辑代码
        }

        //do someting 业务逻辑代码
        Toast.makeText(this,"从业务逻辑的角度看:首页可见了",Toast.LENGTH_SHORT).show();
    }

    private void startPreloadActivity() {
        Intent intent = new Intent(this,SplashActivity.class);
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    private void initView() {
        mHintTextView = (TextView)findViewById(R.id.hint_tv);
    }

}
