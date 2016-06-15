package com.feicui.news;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.feicui.news.Bean.News;
import com.feicui.news.activity.LogonScreenActivity;
import com.feicui.news.activity.WebActivity;
import com.feicui.news.adapter.NewAdapter;
import com.feicui.news.util.BitmapUtils;
import com.feicui.news.util.HttpUtils;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private ImageButton mbtn1;
    private ImageButton mbtn2;
    private SlidingMenu mSlidingMenu;
    private ListView mListView;
    private List<News.DataBean>         mData;
    private NewAdapter                 mAdapter;
    private Gson                        gson;
    private ImageLoader mImageLoader;
    private RequestQueue mQueue;
    private LinearLayout mfenxiang;
    OnekeyShare oks = new OnekeyShare();
    private static final String TAG = "MainActivity";
    private static final String path="http://118.244.212.82:9092/" +
            "newsClient/news_list?ver=1&subid=1&dir=1&nid=5&stamp=20140321&cnt=20" ;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mQueue= Volley.newRequestQueue(getApplicationContext());
        mImageLoader=new ImageLoader(mQueue,new BitmapUtils());
        mAdapter=new NewAdapter(this,mImageLoader);
        mListView.setAdapter(mAdapter);
        new AsyncTask<String, Void, News>() {
            @Override
            protected News doInBackground(String... params) {
                String info = HttpUtils.getInfo(path);
                mData=new ArrayList<>();
                gson=new Gson();
                News bean = gson.fromJson(info, News.class);
                return bean;
            }

            @Override
            protected void onPostExecute(News dataBean) {
                mAdapter.addData(dataBean.getData());
                mAdapter.notifyDataSetChanged();
                super.onPostExecute(dataBean);
            }
        }.execute();
    }

    private void initView() {
        mListView= (ListView) findViewById(R.id.lv_list);
        mbtn1= (ImageButton) findViewById(R.id.ibtn1);
        mbtn2= (ImageButton) findViewById(R.id.ibtn2);
        mbtn1.setOnClickListener(this);
        mbtn2.setOnClickListener(this);
        //侧拉菜单初始化
        mSlidingMenu = new SlidingMenu(this);
        //侧拉菜单的触摸响应范围
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN );
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置侧拉菜单的偏移量
        mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_width);
        mSlidingMenu.setMenu(R.layout.left_list);
        mSlidingMenu.setSecondaryMenu(R.layout.right_list);
        mButton = (Button) findViewById(R.id.list_btn_deng);
        mButton.setOnClickListener(this);
        mfenxiang= (LinearLayout) findViewById(R.id.fenxiang);
        mfenxiang.setOnClickListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(MainActivity.this, WebActivity.class);
               intent.putExtra("url",mAdapter.getItem(position).getLink());
               startActivity(intent);
           }
       });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn1:
                mSlidingMenu.toggle();
                break;
            case R.id.ibtn2:
                mSlidingMenu.toggle();
                mSlidingMenu.showSecondaryMenu();
                break;
            case R.id.list_btn_deng:
                Intent intent=new Intent(MainActivity.this, LogonScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.fenxiang:
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle(getString(R.string.app_name));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(this);
                break;
        }
    }
}
