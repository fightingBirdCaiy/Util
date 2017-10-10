package util.learn.caiy.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2017/10/10.
 */

public class InnerViewHolder extends RecyclerView.ViewHolder{

    private Context mContext;

    private TextView mContentView;

    public InnerViewHolder(View itemView) {
        super(itemView);
        mContentView = (TextView)itemView.findViewById(R.id.content_v);
    }

    public void onBind(int position) {
        mContentView.setText("嵌套的recyclerview中的内容" + position);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public static int getLayoutId(){
        return R.layout.item_inner;
    }
}
