package util.learn.caiy.com.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.util.ToolUtil;

/**
 * Created by yongc on 2017/10/18.
 */

public class CustomViewActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {
        showPromo1TextView();


        showPromo2TextView();
    }

    private void showPromo1TextView() {
        TextView promoTextView = (TextView)findViewById(R.id.promo_tv);
        promoTextView.setBackgroundResource(R.drawable.promo_tv_bg);
        Drawable drawable = promoTextView.getBackground();
        if(drawable != null && drawable instanceof GradientDrawable){
            ((GradientDrawable) drawable).setStroke(ToolUtil.dip2px(this,0.5f),getResources().getColor(R.color.colorRed));
            ((GradientDrawable) drawable).setCornerRadii(new float[]{
                    (float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)
                    ,(float)ToolUtil.dip2px(this,180f)});
        }
        promoTextView.setPadding(ToolUtil.dip2px(this,3f),ToolUtil.dip2px(this,1f),ToolUtil.dip2px(this,3f),ToolUtil.dip2px(this,1f));
    }

    private void showPromo2TextView() {
        TextView promo2TextView = (TextView)findViewById(R.id.promo2_tv);
        promo2TextView.setBackgroundResource(R.drawable.promo2_tv_bg);
        Drawable drawable = promo2TextView.getBackground();
        if(drawable != null && drawable instanceof GradientDrawable){
            ((GradientDrawable) drawable).setStroke(ToolUtil.dip2px(this,0.5f),getResources().getColor(R.color.colorBlack));
            ((GradientDrawable) drawable).setCornerRadii(new float[]{
                    (float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)
                    ,(float)ToolUtil.dip2px(this,3f)});
        }
        promo2TextView.setPadding(ToolUtil.dip2px(this,3f),ToolUtil.dip2px(this,1f),ToolUtil.dip2px(this,3f),ToolUtil
                .dip2px(this,1f));
    }
}
