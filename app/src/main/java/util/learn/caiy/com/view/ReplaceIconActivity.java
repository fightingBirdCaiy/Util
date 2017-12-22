package util.learn.caiy.com.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2017/12/5.
 */

public class ReplaceIconActivity extends Activity{

    private Button normalButton;
    private Button elevenButton;

    private PackageManager mPm;
    private ComponentName mDefaultName;
    private ComponentName m11Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_icon);
        initView();
        initData();
    }

    private void initData() {
        mPm = getApplicationContext().getPackageManager();
        mDefaultName = new ComponentName(getBaseContext(),"util.learn.caiy.com.util.MainActivity");
        m11Name = new ComponentName(getBaseContext(),"util.learn.caiy.com.util.ElevenAlias");
    }

    private void initView() {
        normalButton = (Button)findViewById(R.id.normal_bt);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableComponent(mDefaultName);
                disableComponent(m11Name);
            }
        });

        elevenButton = (Button)findViewById(R.id.eleven_bt);
        elevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableComponent(m11Name);
                disableComponent(mDefaultName);
            }
        });
        Toast.makeText(this,"测试覆盖安装",Toast.LENGTH_LONG).show();
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
