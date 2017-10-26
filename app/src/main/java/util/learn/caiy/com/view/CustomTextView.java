package util.learn.caiy.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2017/10/26.
 */

public class CustomTextView extends TextView{

    private Paint mPaint;

    private String mDetailInfo = "";

    public CustomTextView(Context context) {
        this(context,null);
    }

    public CustomTextView(Context context,
                          @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextView(Context context,
                          @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(ToolUtil.dip2px(getContext(),1f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        TextPaint paint = getPaint();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();

        int top = fontMetricsInt.top;
        int ascent = fontMetricsInt.ascent;
        int descent = fontMetricsInt.descent;
        int bottom = fontMetricsInt.bottom;
        int baseLine = getBaseline();

        mPaint.setColor(getResources().getColor(R.color.colorRed));
        canvas.drawLine(0,0,getWidth(),0,mPaint);//上边线 -ascent
        canvas.drawLine(0,baseLine,getWidth(),baseLine,mPaint);//基线
        canvas.drawLine(0,baseLine+descent,getWidth(),baseLine+descent,mPaint);//descent

        mDetailInfo = calculateDetailInfo();
    }

    public String calculateDetailInfo(){
        TextPaint paint = getPaint();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        getBaseline();
        StringBuilder builder = new StringBuilder();
        builder.append("font bottom="+fontMetricsInt.bottom)
                .append("\n")
                .append("font descent="+fontMetricsInt.descent)
                .append("\n")
                .append("font ascent="+fontMetricsInt.ascent)
                .append("\n")
                .append("font top="+fontMetricsInt.top)
                .append("\n")
                .append("font leading="+fontMetricsInt.leading)
                .append("\n")
                .append("\n")
                .append("baseline="+getBaseline())
                .append("\n")
                .append("bottom="+getBottom())
                .append("\n")
                .append("top="+getTop())
                .append("\n")
                .append("\n")
                .append("小结：")
                .append("\n")
                .append("1.TextView高度同font bottom、font top、font leading无关")
                .append("\n")
                .append("\n")
                .append("2.TextView高度 == bottom - top, 同样等于 descent - ascent, 同样等于 descent + baseline")
                .append("\n")
                .append("\n")
                .append("3.TextView自带的上下padding无法去除");

        return builder.toString();
    }

    public String getDetailInfo(){
        return mDetailInfo;
    }
}
