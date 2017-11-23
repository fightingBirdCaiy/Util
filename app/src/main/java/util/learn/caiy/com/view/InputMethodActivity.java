package util.learn.caiy.com.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2017/11/17.
 * 请修改 android:windowSoftInputMode 属性为adjustResize 或者 adjustPan进行测试
 * 参考自https://www.douban.com/note/577678148/
 */

public class InputMethodActivity extends Activity {

    private Button mSwitchButton;
    private EditText mEditTextView;

    private boolean show = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_method);
        initView();
        initData();
    }

    private void initData() {
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputMethod(show);
                show = !show;
            }
        });
    }

    private void initView() {
        mSwitchButton = (Button)findViewById(R.id.switch_btn);
        mEditTextView = (EditText)findViewById(R.id.edit_tv);

    }

    private void showInputMethod(boolean show) {
        //自动弹出键盘
        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        //切换输入法
//        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        if (show) {
            inputManager.showSoftInput(mEditTextView,InputMethodManager.SHOW_FORCED);
        } else {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }
}
