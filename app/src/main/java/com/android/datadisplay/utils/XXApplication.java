package com.android.datadisplay.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/2/25.
 */
public class XXApplication extends Application {
    public static Context context;
    public static Handler mainHandler;
    public static boolean state_one=true;
    public static boolean state_two=true;
    public static boolean state_three=true;
    public static boolean state_four=true;
    public static final String SERVER_URL="192.168.1.106";
    public static final int SERVER_PORT = 9999;
    public static final String TAG_CESHI = "测试1";
    public static final String UUID = "00000000-0000-0000-0000-000000000000";
    public static List<Activity> activityList = new ArrayList();    //记录打开的activity
 //   public static List<DLHBean> downLoadHistoryList = new ArrayList();    //记录下载的文件
    public static ExecutorService executor = Executors.newFixedThreadPool(5);
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();
    }
}
