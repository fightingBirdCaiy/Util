package util.learn.caiy.com.listview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import util.learn.caiy.com.util.R;

/**
 * Created by admin on 2018/1/18.
 */

public class CtrAdapter extends BaseAdapter{

    private static final String TAG = "CtrAdapter";

    private static final long STATISTIC_DELAY = 1000L;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDatas = new ArrayList();

    private AbsListView.RecyclerListener mRecyclerListener;

    private SparseArray<StatisticRunnable> statisticRunnableMap = new SparseArray<StatisticRunnable>(20);

    public CtrAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = Arrays.asList("item00","item01","item02","item03","item04","item05","item06","item07","item08","item09"
                                    ,"item10","item11","item12","item13","item14","item15","item16","item17","item18","item19");

        mRecyclerListener = new AbsListView.RecyclerListener(){

            @Override
            public void onMovedToScrapHeap(View view) {
                if(view != null){
                    Object positionTag = view.getTag(R.id.statistic_position);
                    if(positionTag != null && positionTag instanceof Integer){
                        int position = (int)positionTag;
                        Log.i(TAG,String.format("onMovedToScrapHeap,position=%d,view=%s",position,view));
                        StatisticRunnable statisticRunnable = getRunnableFromCache(position);
                        if(statisticRunnable != null){
                            view.removeCallbacks(statisticRunnable);
                        }
                    }
                }

            }
        };
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Log.i(TAG,String.format("getView方法调用了:position=%d,convertView=%s,parent=%s",position,convertView,parent));

        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_ctr,parent,false);
            holder = new ViewHolder();
            holder.contentTextView = (TextView)convertView.findViewById(R.id.content_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.contentTextView.setText(mDatas.get(position));

        StatisticRunnable statisticRunnable = getRunnableInstance(position);
        convertView.postDelayed(statisticRunnable,STATISTIC_DELAY);
        convertView.setTag(R.id.statistic_position,position);
        return convertView;
    }

    private StatisticRunnable getRunnableInstance(int postion){
        StatisticRunnable cachedRunnable = getRunnableFromCache(postion);
        if(cachedRunnable == null){
            cachedRunnable = new StatisticRunnable(postion);
            statisticRunnableMap.put(postion,cachedRunnable);
        }
        return cachedRunnable;
    };

    private StatisticRunnable getRunnableFromCache(int postion){
        StatisticRunnable cachedRunnable = statisticRunnableMap.get(postion);
        return cachedRunnable;
    };

    public AbsListView.RecyclerListener getRecyclerListener() {
        return mRecyclerListener;
    }

    private static class ViewHolder{
        TextView contentTextView;
    }

    private static class StatisticRunnable implements Runnable{

        private int position = -1;

        public StatisticRunnable(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            Log.i(TAG,String.format("statisticRunnable调用了:position=%d",position));
        }
    }
}
