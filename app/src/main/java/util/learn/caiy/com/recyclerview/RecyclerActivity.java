package util.learn.caiy.com.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2017/10/10.
 */

public class RecyclerActivity extends Activity{

    private RecyclerView mRecyclerView;
    private OuterAdapter mRecyclerAdaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.main_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerAdaper = new OuterAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdaper);
    }
}
