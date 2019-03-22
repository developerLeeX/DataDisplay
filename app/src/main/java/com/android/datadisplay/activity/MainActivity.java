package com.android.datadisplay.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.fragment.BaseFragment;
import com.android.datadisplay.fragment.LeftFragment;
import com.android.datadisplay.fragment.ProjectFragment;
import com.android.datadisplay.utils.ToastUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
    public static final String TAG_START="startTag";
    public static final String TAG_START_MAIN = "main";
    public static final String TAG_START_LEFT = "left";

    public static final String[] TAG_FLAGS = {"TAG_LEFT","TAG_PROJECT","TAG_MONITOR","TAG_PLC","TAG_DEVICE"};

    public int runFragmentCount = 0;        //记录当前存活的的fragment个数
    public BaseFragment mCurrentFragment;   //记录当前显示的fragment实例
    public FragmentManager fm;
    private ImageButton ib_menu;
    public ProgressBar progressBar;
    private TextView tv_title;
    public int byID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        progressBar = (ProgressBar) findViewById(R.id.myProBar);
        tv_title = (TextView) findViewById(R.id.tv_myTitle);
        setBehindContentView(R.layout.main_left);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(300);   //屏幕预留宽度
        initView();
        initData();
    }

    private void initData() {
        ib_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggle();
            }
        });

    }

    private void initView() {
         fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();   //开启事务
        ProjectFragment projectFragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_START,TAG_START_MAIN);

        projectFragment.setArguments(bundle);
        transaction.replace(R.id.fl_main,projectFragment,TAG_FLAGS[1]);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fl_left, new LeftFragment(), TAG_FLAGS[0]);
        transaction.commit();
    }
    public void toggle() {

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.toggle();
    }
    public void setMyProgress(int progress){
        progressBar.setProgress(progress);
    }
    public void setMyTitle(String string){
        tv_title.setText(string);
    }
    public BaseFragment getmCurrentFragment(){
        return mCurrentFragment;
    }
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0 && runFragmentCount==2&&!getSlidingMenu().isMenuShowing()){
                exit();
                return true;
            }
            return super.onKeyDown(keyCode,event);
    }
    private void exit(){
        if ((System.currentTimeMillis() - mExitTime)>2000){
            ToastUtil.toast("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }
}
