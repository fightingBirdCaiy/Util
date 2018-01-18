package util.learn.caiy.com.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import util.learn.caiy.com.listview.CtrAdapter;
import util.learn.caiy.com.listview.CtrListView;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/18.
 */

public class ListViewCtrActivity extends Activity{

    private static final String TAG = "ListViewCtrActivity";

    private CtrListView mCtrListView;
    private CtrAdapter mCtrAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_ctr);

        initView();
    }

    private void initView() {
        mCtrListView = (CtrListView)findViewById(R.id.ctr_lv);
        mCtrAdapter = new CtrAdapter(this);
        mCtrListView.setRecyclerListener(mCtrAdapter.getRecyclerListener());//这句需要放在setAdapter的前面
        mCtrListView.setAdapter(mCtrAdapter);

        Button reloadBtn = (Button)findViewById(R.id.reload_btn);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCtrAdapter.notifyDataSetChanged();
            }
        });
    }
}
