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
    private static final String VIDEO_ORIGIN_URL = "http://27.221.48.205/6571DA84D014B841CCB0B33C2F/03000B03005A5D843223CD048750EBD1FAAC9E-1AA6-D48C-72F5-8F54FBB7BE9F.mp4?ccode=03020101&duration=197&expire=18000&psid=5f97085fb6f51292ba6d89ed50045887&ups_client_netip=70fcc231&ups_ts=1519462404&ups_userid=&utid=yxUYE2woGnYCAXD8wjGCtq1e&vid=XMzMyMjg3MTQzNg%3D%3D&vkey=A15f92de58d9b39f8a654a022a622106c";
//    private static final String VIDEO_ORIGIN_URL = "http://jmvideo2.jumei.com/MQ_E_E/MTUxODIyNjYxMTQwMw_E_E/MzEwMjc2MA_E_E/L2hvbWUvd3d3L2xvZ3MvdmlkZW8vZmlsZV84OTkyMTMtNjVjYTg4M2NlMmFmN2NjYTBkZGViNzBmODU1YzIxZDQvdmlkZW8ubXA0_default.mp4";

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
