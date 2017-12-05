package util.learn.caiy.com.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2017/12/5.
 */

public class NoMenuEditActivity extends Activity{

    private NoMenuEditText mNoMenuEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_menu_edit);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        mNoMenuEditText = (NoMenuEditText)findViewById(R.id.no_menu_et);
    }

}
