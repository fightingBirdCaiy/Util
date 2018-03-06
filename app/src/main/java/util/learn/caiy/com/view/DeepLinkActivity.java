package util.learn.caiy.com.view;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/5.
 */

public class DeepLinkActivity extends Activity{

    private static final String TAG = "DeepLinkActivity";

    private EditText jumpUrlEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        getDataFromIntent();

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Uri data = getIntent().getData();
        if(data != null){
            String dataUrl = data.toString();
            String msg = String.format("DeepLinkActivity接收到外部链接\n:%s",dataUrl);
            Log.i(TAG,msg);
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        String initUrl = "jumeimall://page/grouponproduct?itemid=ht171216p2171531t4zh&type=global_combination_deal"
                + "&link_source=caiyongApp"
                + "&link_name=返回caiyong"
                + "&backmode=xxl&backurl=caiyong://2018.happy.new.year?msg1=新年快乐&msg2=万事大吉";

        initUrl = "jumeimall://page/alldetail?itemid=ht1499411322p3160352&type=global_pop&source=toutiao&sell_label"
                + "=xxx&selltype=xxx&sellparams=xxx&backurl=snssdk143://";

        jumpUrlEditText = (EditText)findViewById(R.id.jump_content_et);
        jumpUrlEditText.setText(initUrl);

        Button toJumeiButton = (Button)findViewById(R.id.to_jumei_btn);
        toJumeiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = jumpUrlEditText.getText().toString();
                if(TextUtils.isEmpty(url)){
                    Toast.makeText(DeepLinkActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                    String msg = String.format("跳转失败:url=%s",url);
                    Log.e(TAG,msg);
                    Toast.makeText(DeepLinkActivity.this,msg,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
