package util.learn.caiy.com.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by admin on 2018/1/4.
 */

public class DragActivity extends Activity{

    private static final String TAG = "DragActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        initView();
    }

    private void initView() {
        View decorView = getWindow().getDecorView();
        if(decorView instanceof ViewGroup){
            ViewGroup decorViewGroup = (ViewGroup)decorView;
            if(decorViewGroup instanceof FrameLayout){
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 0;
                params.topMargin = getTopMargin();
                View deepLinkView = new DragView(this);
                decorViewGroup.addView(deepLinkView, params);
            }else {
                Log.e(TAG,"一键返回view显示失败:decorView不是FrameLayout");
            }
        }
    }

    private int getTopMargin() {
        int height = ToolUtil.getHeight(this);
        float margin = height * (1-0.618f);
        return (int)margin;
    }
}
