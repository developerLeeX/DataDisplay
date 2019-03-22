package com.android.datadisplay.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.adapter.MonitorAdapter;
import com.android.datadisplay.bean.MonitorBean;
import com.android.datadisplay.jdbc.MyDao;
import com.android.datadisplay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MonitorFragment extends BaseFragment {
    private TextView tv_default;
    private ListView lv_monitor;
    private List<MonitorBean> monitorList = new ArrayList<>();
    private MonitorAdapter myAdapter;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.frag_monitor,null);
        tv_default = view.findViewById(R.id.tv_default_monitor);
        lv_monitor = view.findViewById(R.id.lv_monitor);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        main.mCurrentFragment = this;
        main.setMyTitle("监测点");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            //当fragment从隐藏到显示时
            main.mCurrentFragment = this;
            main.setMyTitle("监测点");
        }
    }

    @Override
    public void initData() {

        new MyAsyncTast().execute();
    }
    private class MyAsyncTast extends AsyncTask{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            main.progressBar.setVisibility(View.VISIBLE);
            main.setMyProgress(0);
            tv_default.setVisibility(View.VISIBLE);
            lv_monitor.setVisibility(View.GONE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {

                Bundle bundle = getArguments();
                String startTag = bundle.getString(main.TAG_START);
                if (main.TAG_START_MAIN.equals(startTag)){
                    monitorList = MyDao.queryMonitor(main.byID);
                }else if (main.TAG_START_LEFT.equals(startTag)){
                    MonitorBean monitorBean = (MonitorBean) bundle.getSerializable("data");
                    monitorList = new ArrayList<>();
                    monitorList.add(monitorBean);
                }
                synchronized (mActivity) {
                    for (int i = 50; i <= 100; i += 25) {
                        Thread.sleep(50);
                        publishProgress(i);
                    }
                }
                } catch(Exception e){
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            main.progressBar.setVisibility(View.GONE);
            if (monitorList.size()==0){
                ToastUtil.toast("暂无数据");
                tv_default.setText("暂无数据");
            }else {
                tv_default.setVisibility(View.GONE);

                lv_monitor.setVisibility(View.VISIBLE);
                myAdapter = new MonitorAdapter(mActivity,monitorList);
                lv_monitor.setAdapter(myAdapter);
            }

        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            main.setMyProgress((Integer) values[0]);
        }
    }

}
