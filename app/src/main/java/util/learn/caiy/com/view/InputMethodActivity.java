package util.learn.caiy.com.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2017/11/17.
 * 请修改 android:windowSoftInputMode 属性为adjustResize 或者 adjustPan进行测试
 * 参考自https://www.douban.com/note/577678148/
 */

public class InputMethodActivity extends Activity {

    private EditText mEditTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_method);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        mEditTextView = (EditText)findViewById(R.id.edit_tv);
        
    }
}
