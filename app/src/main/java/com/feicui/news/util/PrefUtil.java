package com.feicui.news.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AAAAA on 2016/6/12.
 */
public class PrefUtil {
    //第一次封装判断
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
    //第二次封装判断是不是第一次进如，要不要引导页面
    public static void setBoolean(Context ctx,String key,boolean value){
        SharedPreferences sp=ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
}
