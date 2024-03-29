package com.android.datadisplay.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/19.
 */
public class SharePerUtil {
    public  static void setBoolean(Context context,String key,Boolean value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    public  static Boolean getBoolean(Context context,String key,Boolean value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,value);
    }
    public  static void setString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    public  static String getString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  sp.getString(key,value);
    }
    public  static void setInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    public  static int getInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return  sp.getInt(key,value);
    }
}
