package util.learn.caiy.com.view;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import util.learn.caiy.com.util.R;

/**
 * Created by yongc on 2017/10/25.
 */

public class TextViewPaddingActivity extends Activity{

    TextView mDescTextView;
    TextView mContent1TextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_padding);
        initView();
    }

    private void initView() {
        mContent1TextView = (TextView) findViewById(R.id.content1_tv);
        mDescTextView = (TextView)findViewById(R.id.desc_tv);

        Paint.FontMetricsInt fontMetricsInt = mContent1TextView.getPaint().getFontMetricsInt();
        StringBuilder builder = new StringBuilder();
        builder.append("bottom="+fontMetricsInt.bottom)
                .append("\n")
                .append("descent="+fontMetricsInt.descent)
                .append("\n")
                .append("ascent="+fontMetricsInt.ascent)
                .append("\n")
                .append("top="+fontMetricsInt.top)
                .append("\n");
        mDescTextView.setText(builder.toString());
    }
}
