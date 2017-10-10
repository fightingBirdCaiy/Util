package util.learn.caiy.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2017/10/10.
 */

public class OuterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "TAG";

    private Context mContext;

    public OuterAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"OuterAdapter onCreateViewHolder方法调用了");
        View rootView = LayoutInflater.from(mContext).inflate(OuterViewHolder.getLayoutId(),parent,false);
        OuterViewHolder outerViewHolder = new OuterViewHolder(rootView,mContext);
        return outerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG,"OuterAdapter onBindViewHolder方法调用了,position=" + position);
        if(holder instanceof OuterViewHolder){
            ((OuterViewHolder)holder).onBind(position);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
