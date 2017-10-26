package util.learn.caiy.com.view;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2017/10/25.
 */

public class TextViewPaddingActivity extends Activity{

    private static final int WHAT_SHOW_PAINT_DETAIL_INFO = 100;

    TextView mDescTextView;
    CustomTextView mContent1TextView;
    EditText mFontSizeEditText;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case WHAT_SHOW_PAINT_DETAIL_INFO:
                    showPaintDetailInfo();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_padding);
        initView();
    }

    private void initView() {
        mContent1TextView = (CustomTextView) findViewById(R.id.content1_tv);
        mDescTextView = (TextView)findViewById(R.id.desc_tv);
        mFontSizeEditText = (EditText)findViewById(R.id.font_size_et);

        mFontSizeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){//单位sp
                    float textSizePx = mContent1TextView.getTextSize();
                    int textSize = ToolUtil.px2dip(TextViewPaddingActivity.this,textSizePx);
                    try {
                        textSize = Integer.parseInt(s.toString());
                        mContent1TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
                        mHandler.removeMessages(WHAT_SHOW_PAINT_DETAIL_INFO);
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(WHAT_SHOW_PAINT_DETAIL_INFO),500);
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(TextViewPaddingActivity.this,"请输入数字，可以是小数",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mHandler.sendMessageDelayed(mHandler.obtainMessage(WHAT_SHOW_PAINT_DETAIL_INFO),500);
    }

    private void showPaintDetailInfo() {
        String detailInfoStr = mContent1TextView.getDetailInfo();
        mDescTextView.setText(detailInfoStr);
    }
}
