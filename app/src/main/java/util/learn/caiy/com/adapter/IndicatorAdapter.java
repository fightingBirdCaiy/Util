package util.learn.caiy.com.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by admin on 2018/3/28.
 */

public class IndicatorAdapter extends PagerAdapter{

    private List<View> mDatas = new ArrayList<>();

    public IndicatorAdapter(Context context,int count){
        for(int i=0; i<count; i++){
            TextView textView = new TextView(context);
            textView.setText("内容"+ i);
            mDatas.add(textView);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDatas.get(position));// 添加页卡
        return mDatas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDatas.get(position));// 删除页卡
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
