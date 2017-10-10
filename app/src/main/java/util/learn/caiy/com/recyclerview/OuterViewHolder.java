package util.learn.caiy.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2017/10/10.
 */

public class OuterViewHolder extends RecyclerView.ViewHolder{

    private static final String TAG = "OuterViewHolder";

    private Context mContext;

    private Button mCollapsibleView;
    private RecyclerView mInnerRecyclerView;
    private InnerAdapter mInnerAdapter;
    private RecyclerView mRecyclerView;

    public OuterViewHolder(View itemView, Context context, RecyclerView recyclerView) {
        super(itemView);
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        mCollapsibleView = (Button)itemView.findViewById(R.id.collapsible_btn);

        mInnerRecyclerView = (RecyclerView)itemView.findViewById(R.id.inner_rv);
        mInnerRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mInnerAdapter = new InnerAdapter(context);
        mInnerRecyclerView.setAdapter(mInnerAdapter);
        mInnerRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(TAG,"onTouch,v=" + v);
                boolean outerCanScroll = event.getAction() == MotionEvent.ACTION_UP;
                CustomLinearLayoutManager manager = (CustomLinearLayoutManager)mRecyclerView.getLayoutManager();
                manager.setCanCustomScrollVertical(outerCanScroll);
                return false;
            }
        });

        mCollapsibleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInnerRecyclerView.getVisibility() == View.VISIBLE){
                    mInnerRecyclerView.setVisibility(View.GONE);
                }else{
                    mInnerRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onBind(int position) {
        mCollapsibleView.setText("可折叠的按钮" + position);
    }

    public static int getLayoutId(){
        return R.layout.item_outer;
    }
}
