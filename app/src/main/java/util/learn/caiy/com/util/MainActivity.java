package util.learn.caiy.com.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;
import util.learn.caiy.com.activity.StartActivity;
import util.learn.caiy.com.activity.VideoCacheActivity;
import util.learn.caiy.com.recyclerview.RecyclerActivity;
import util.learn.caiy.com.view.CustomViewActivity;
import util.learn.caiy.com.view.DeepLinkActivity;
import util.learn.caiy.com.view.DragActivity;
import util.learn.caiy.com.view.InputMethodActivity;
import util.learn.caiy.com.view.ListViewCtrActivity;
import util.learn.caiy.com.view.LongPictureActivity;
import util.learn.caiy.com.view.NoMenuEditActivity;
import util.learn.caiy.com.view.ReplaceIconActivity;
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
                int width = ToolUtil.getWidth(MainActivity.this);
                int height = ToolUtil.getHeight(MainActivity.this);
                Toast.makeText(MainActivity.this,"屏幕宽度:" +  width + ",屏幕高度:" + height + "，屏幕密度比是:" + density,Toast.LENGTH_LONG).show();
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

        View longPictureBtn = findViewById(R.id.long_picture_btn);
        longPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakInfoTextViewIntent = new Intent(MainActivity.this, LongPictureActivity.class);
                startActivity(breakInfoTextViewIntent);
            }
        });

        View inputMethodBtn = findViewById(R.id.input_method_btn);
        inputMethodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent breakInfoTextViewIntent = new Intent(MainActivity.this, InputMethodActivity.class);
                startActivity(breakInfoTextViewIntent);
            }
        });



        View noMenuEditBtn = findViewById(R.id.no_menu_edit_btn);
        noMenuEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noMenuEditIntent = new Intent(MainActivity.this, NoMenuEditActivity.class);
                startActivity(noMenuEditIntent);
            }
        });

        View replaceIconBtn = findViewById(R.id.replace_icon_btn);
        replaceIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replaceIconIntent = new Intent(MainActivity.this, ReplaceIconActivity.class);
                startActivity(replaceIconIntent);
            }
        });

        View dragIconBtn = findViewById(R.id.drag_icon_btn);
        dragIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dragIconIntent = new Intent(MainActivity.this, DragActivity.class);
                startActivity(dragIconIntent);
            }
        });

        View deepLinkBtn = findViewById(R.id.deep_link_btn);
        deepLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deepLinkIntent = new Intent(MainActivity.this, DeepLinkActivity.class);
                startActivity(deepLinkIntent);
            }
        });

        View listViewCtrBtn = findViewById(R.id.list_view_ctr_btn);
        listViewCtrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctrIntent = new Intent(MainActivity.this, ListViewCtrActivity.class);
                startActivity(ctrIntent);
            }
        });

        View startBtn = findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        View videoCacheBtn = findViewById(R.id.video_cache_btn);
        videoCacheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoCacheActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showOrHideImmersiveView(boolean show){
        int statusBarHeight = ToolUtil.getStatusBarHeight(this);

        int paddingTop = show ? statusBarHeight : 0;
        int height = ToolUtil.dip2px(this,50) + paddingTop;

        mImmersiveTitleView.setPadding(0,paddingTop,0,0);

        ViewGroup.LayoutParams params = mImmersiveTitleView.getLayoutParams();
        params.height = height;
        mImmersiveTitleView.setLayoutParams(params);

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
