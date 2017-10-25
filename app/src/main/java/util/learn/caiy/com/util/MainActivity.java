package util.learn.caiy.com.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;
import util.learn.caiy.com.recyclerview.RecyclerActivity;
import util.learn.caiy.com.view.CustomViewActivity;
import util.learn.caiy.com.view.TextViewPaddingActivity;

public class MainActivity extends Activity {

    private View mRootView;
    private View mImmersiveTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mRootView = findViewById(R.id.main_root_view);

        mImmersiveTitleView = findViewById(R.id.immersive_title_view);

        View densityView = findViewById(R.id.density_view);
        densityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float density = getResources().getDisplayMetrics().density;
                Toast.makeText(MainActivity.this,"屏幕密度比是:" + density,Toast.LENGTH_SHORT).show();
            }
        });

        View popupWindow = findViewById(R.id.popup_window);
        popupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });

        View openImmersiveView = findViewById(R.id.open_immersive);
        openImmersiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

                showOrHideImmersiveView(true);
            }
        });

        View closeImmersiveView = findViewById(R.id.close_immersive);
        closeImmersiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                showOrHideImmersiveView(false);
            }
        });

        View recyclerView = findViewById(R.id.recyclerview_btn);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recyclerActivityIntent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(recyclerActivityIntent);
            }
        });

        View customViewActivity = findViewById(R.id.custom_view_btn);
        customViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recyclerActivityIntent = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(recyclerActivityIntent);
            }
        });

        View textViewPaddingActivity = findViewById(R.id.text_view_padding_btn);
        textViewPaddingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recyclerActivityIntent = new Intent(MainActivity.this, TextViewPaddingActivity.class);
                startActivity(recyclerActivityIntent);
            }
        });
    }

    private void showOrHideImmersiveView(boolean show){
        int height = show ? ToolUtil.getStatusBarHeight(this) : 0;

        ViewGroup.LayoutParams params = mImmersiveTitleView.getLayoutParams();
        params.height = height;
        mImmersiveTitleView.setLayoutParams(params);

        if(show){
            mImmersiveTitleView.setVisibility(View.VISIBLE);
        }else{
            mImmersiveTitleView.setVisibility(View.GONE);
        }
        mImmersiveTitleView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mImmersiveTitleView.requestLayout();//防止布局未刷新成最新的
            }
        },200);
    }

    private void initPopupWindow() {
        View content = LayoutInflater.from(this).inflate(R.layout.popupwindow_main,null);

        content.setFocusable(true);// 设置view能够接听事件
        content.setFocusableInTouchMode(true); // 设置view能够接听事件
        content.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
//                if (arg1 == KeyEvent.KEYCODE_BACK) {
//
//                    return true;
//                }
                Toast.makeText(MainActivity.this,"back在popupwindow中点击了",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        PopupWindow popupWindow = new PopupWindow(content, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
}
