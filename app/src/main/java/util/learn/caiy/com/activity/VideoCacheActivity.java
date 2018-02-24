package util.learn.caiy.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import util.learn.caiy.com.util.R;
import util.learn.caiy.com.video.cache.VideoCacheManager;
import util.learn.caiy.com.video.cache.VideoPreloadManager;

/**
 * Created by admin on 2018/2/24.
 */

public class VideoCacheActivity extends Activity{

//    private static final String VIDEO_ORIGIN_URL = "https://raw.githubusercontent.com/danikula/AndroidVideoCache/master/files/origin1.mp4";
    private static final String VIDEO_ORIGIN_URL = "http://jmvideo2.jumei.com/MQ_E_E/MTUxODIyNjYxMTQwMw_E_E/MzEwMjc2MA_E_E/L2hvbWUvd3d3L2xvZ3MvdmlkZW8vZmlsZV84OTkyMTMtNjVjYTg4M2NlMmFmN2NjYTBkZGViNzBmODU1YzIxZDQvdmlkZW8ubXA0_default.mp4";

    private Button mStartButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_cache);

        initView();
    }

    private void initView() {
        mStartButton = (Button)findViewById(R.id.start_btn);
        mCancelButton = (Button)findViewById(R.id.cancel_btn);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = VideoCacheManager.getInstance(VideoCacheActivity.this).getProxyUrl(VIDEO_ORIGIN_URL);
                VideoPreloadManager.getInstance(VideoCacheActivity.this).preloadVideo(url);
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = VideoCacheManager.getInstance(VideoCacheActivity.this).getProxyUrl(VIDEO_ORIGIN_URL);
                VideoPreloadManager.getInstance(VideoCacheActivity.this).cancelPreLoadVideo(url);
            }
        });
    }
}
