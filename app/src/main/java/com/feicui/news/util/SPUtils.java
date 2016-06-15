package com.feicui.news.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 包含保存和取出数据方法的类用来验证用户名和密码是否相同
 * Created by AAAAA on 2016/6/13.
 */
public class SPUtils {
    //SharedPreferences存储数据
    private static SharedPreferences mPreferences;
    private static final String NAME="ViewPager";

    /**
     *获取数据
     * @param context
     * @param key
     * @param value
     */
  public static void putBoolen(Context context,String key, boolean value){
      SharedPreferences sp=getPreferences(context);
      SharedPreferences.Editor editor=sp.edit();
      editor.putBoolean(key,value);
      editor.apply();
  }

    /**
     *获取数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolen(Context context,String key){
        return getBoolen(context,key,false);
    }

    /**
     *判断
     * @param context
     * @param key
     * @param defvalue
     * @return
     */
    private static boolean getBoolen(Context context, String key, boolean defvalue) {
        SharedPreferences sp=getPreferences(context);
        return sp.getBoolean(key,defvalue);
    }

    /**
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value){
        SharedPreferences sp=getPreferences(context);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString(key,value);
        edit.apply();
    }

    /**
     *
     * @param context
     * @return
     */
    private static SharedPreferences getPreferences(Context context) {
        if (mPreferences==null){
            mPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        }
        return mPreferences;

    }

    /**
     * 获得一个String类型的数据，如果没有则返回null
     * @param context
     * 上下文
     * @param key
     * sp里的key
     * @return  拿到返回的结果
     */
    public static String getString(Context context,String key){
        return getString(context,key,null);
    }

    /**
     * 获得String类型的数据
     * @param context
     * 上下文
     * @param key
     * sp里的key
     * @param defvalue
     * sp里的value
     * @return
     */
    private static String getString(Context context, String key, String defvalue) {
        SharedPreferences sp=getPreferences(context);
        return sp.getString(key,defvalue);
    }
}
