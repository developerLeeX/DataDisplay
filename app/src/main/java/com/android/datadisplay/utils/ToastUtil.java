package com.android.datadisplay.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/27.
 * 单例吐司  能够连续弹吐司
 */
public class ToastUtil {
    private static Toast toast;
    public static void toast(String text){
        if(toast == null){
            toast = Toast.makeText(XXApplication.context,text,Toast.LENGTH_SHORT);
            //toast.setView();
        }else {
            toast.setText(text);  //不为空直接改变文本
        }
        toast.show();
    }
    public static void toast2UI(Activity activity, final String message){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toast(message);
            }
        });
    }
}
