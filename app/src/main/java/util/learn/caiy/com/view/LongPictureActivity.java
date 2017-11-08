package util.learn.caiy.com.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import util.learn.caiy.com.util.LongPictureUtil;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2017/11/7.
 */

public class LongPictureActivity extends Activity{

    private static final String TAG = "LongPictureActivity";

    private Handler mHandler = new Handler();

    private LinearLayout mContentLayout;
    private ImageView mGeneratedImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_picture);
        initView();
    }

    private void initView() {
        mContentLayout = (LinearLayout)findViewById(R.id.content_ll);
        mGeneratedImageView = (ImageView) findViewById(R.id.generated_iv);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = LongPictureUtil.getLinearLayoutBitmap(mContentLayout);
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                ViewGroup.LayoutParams params = mGeneratedImageView.getLayoutParams();
                params.width = width;
                params.height = height;
                Log.i(TAG,"generated bitmap,width="+ width +",height=" + height);
                mGeneratedImageView.setLayoutParams(params);
                mGeneratedImageView.setImageBitmap(bitmap);
            }
        },2000);
    }
}
