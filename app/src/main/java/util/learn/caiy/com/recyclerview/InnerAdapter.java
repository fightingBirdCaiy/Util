package util.learn.caiy.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2017/10/10.
 */

public class InnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "TAG";

    private Context mContext;

    public InnerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"InnerAdapter onCreateViewHolder方法调用了");
        View rootView = LayoutInflater.from(mContext).inflate(InnerViewHolder.getLayoutId(),parent,false);
        InnerViewHolder innerViewHolder = new InnerViewHolder(rootView);
        innerViewHolder.setContext(mContext);
        return innerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG,"InnerAdapter onBindViewHolder方法调用了,position=" + position);
        if(holder instanceof InnerViewHolder){
            ((InnerViewHolder)holder).onBind(position);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
