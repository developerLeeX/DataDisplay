package com.android.datadisplay.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.adapter.PLCAdapter;
import com.android.datadisplay.bean.PLCBean;
import com.android.datadisplay.jdbc.MyDao;
import com.android.datadisplay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class PLCFragment extends BaseFragment {
    private TextView tv_default;
    private ListView lv_plc;
    private List<PLCBean> plcList = new ArrayList<>();
    private PLCAdapter myAdapter;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.frag_plc,null);
        tv_default = view.findViewById(R.id.tv_default_plc);
        lv_plc = view.findViewById(R.id.lv_plc);
        return view;
    }

    @Override
    public void initData() {

        new MyAsyncTast().execute();
    }
    @Override
    public void onResume() {
        super.onResume();
        main.mCurrentFragment = this;
        main.setMyTitle("PLC");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            //当fragment从隐藏到显示时
            main.mCurrentFragment = this;
            main.setMyTitle("PLC");
        }
    }
    private class MyAsyncTast extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            main.progressBar.setVisibility(View.VISIBLE);
            main.setMyProgress(0);
            tv_default.setVisibility(View.VISIBLE);
            lv_plc.setVisibility(View.GONE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {

                Bundle bundle = getArguments();
                String startTag = bundle.getString(main.TAG_START);
                if (main.TAG_START_MAIN.equals(startTag)){
                    plcList = MyDao.queryPLCByID(main.byID);
                }else if (main.TAG_START_LEFT.equals(startTag)){
                    PLCBean plcBean = (PLCBean) bundle.getSerializable("data");
                    plcList = new ArrayList<>();
                    plcList.add(plcBean);
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
            if (plcList.size()==0){
                ToastUtil.toast("暂无数据");
                tv_default.setText("暂无数据");
            }else {
                tv_default.setVisibility(View.GONE);

                lv_plc.setVisibility(View.VISIBLE);
                myAdapter = new PLCAdapter(mActivity,plcList);
                lv_plc.setAdapter(myAdapter);
            }

        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            main.setMyProgress((Integer) values[0]);
        }
    }

}
