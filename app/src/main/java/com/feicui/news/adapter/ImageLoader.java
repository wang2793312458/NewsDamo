package com.feicui.news.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AAAAA on 2016/6/13.
 */
public class ImageLoader {
    private ImageView mImageView;
    private String imageUrl;

    /**
     * 加载图片,使用线程Thread
     * @param imageView
     * @param url
     */
    public void showImageByThread(ImageView imageView, final String url){
        mImageView = imageView;
        imageUrl = url;
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Bitmap bitmap=getBitmapFromUrl(url);
                    Message message=Message.obtain();
                 //   将message的obj设置为bitmap
                    message.obj=bitmap;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 创建一个通过Url转化为Bitmap
     * @param imageUrl
     * @return
     * @throws Exception
     */
    private Bitmap getBitmapFromUrl(String imageUrl)throws Exception {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url=new URL(imageUrl);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            is=new BufferedInputStream(connection.getInputStream());
            bitmap= BitmapFactory.decodeStream(is);//将is解析为我们需要的bitmap
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            is.close();
        }
        return null;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap) msg.obj);
            //对图片的url进行判断,然后再获得不骗,避免了缓存图片的影响
            if (mImageView.getTag().equals(imageUrl)){
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }

        }
    };
}
