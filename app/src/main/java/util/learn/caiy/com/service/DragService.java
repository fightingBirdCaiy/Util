package util.learn.caiy.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
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
        mDragManager = new DragWindowManager(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
