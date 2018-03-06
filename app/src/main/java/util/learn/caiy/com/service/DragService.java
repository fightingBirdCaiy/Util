package util.learn.caiy.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;
import util.learn.caiy.com.view.DragWindowManager;

/**
 * Created by admin on 2018/1/8.
 */

public class DragService extends Service{

    private DragWindowManager mDragManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this,"您的手机没有打开悬浮窗权限,请在设置界面中打开该权限！",Toast.LENGTH_LONG).show();
                return;
            }
        }
        mDragManager = new DragWindowManager(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
