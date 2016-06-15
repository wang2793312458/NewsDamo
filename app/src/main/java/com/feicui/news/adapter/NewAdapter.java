package com.feicui.news.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.feicui.news.Bean.News;
import com.feicui.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAAAA on 2016/6/13.
 */
public class NewAdapter extends BaseAdapter {
    private static final String              TAG   = "NewsListActivity";
    private              List<News.DataBean> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private com.android.volley.toolbox.ImageLoader mImageLoader;
    //上下文
    public NewAdapter(Context context, com.android.volley.toolbox.ImageLoader imageLoader) {
        mImageLoader=imageLoader;
        mInflater = LayoutInflater.from(context);
    }

    public void addData(List<News.DataBean> bean) {
        mData.addAll(bean);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public News.DataBean getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 将对应的信息显示在主界面的对应位置
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if (convertView==null){
            Log.d(TAG, "getView卡都送快点吗: ");
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_list_news,null);
            viewHolder.item_tv1= (TextView) convertView.findViewById(R.id.item_tv1);
            viewHolder.item_tv2= (TextView) convertView.findViewById(R.id.item_tv2);
            viewHolder.item_tv3= (TextView) convertView.findViewById(R.id.item_tv3);
            Log.d(TAG, "getView卡都送快点吗1: ");
            viewHolder.imageView1= (NetworkImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
            Log.d(TAG, "getView卡都送快点吗2: ");
        }
       News.DataBean bean=mData.get(position);
        Log.d(TAG, "getView卡都送快点吗3: ");
        viewHolder.imageView1.setDefaultImageResId(R.mipmap.cccc);
        viewHolder.imageView1.setImageUrl(bean.getIcon(), mImageLoader);
        viewHolder.item_tv1.setText(bean.getTitle());
        viewHolder.item_tv2.setText(bean.getSummary());
        viewHolder.item_tv3.setText(bean.getStamp());
        return convertView;
    }
    public class ViewHolder{
        TextView item_tv1;
        TextView item_tv2;
        TextView item_tv3;
        NetworkImageView imageView1;

    }
}
