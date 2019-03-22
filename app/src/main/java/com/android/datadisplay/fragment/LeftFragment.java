package com.android.datadisplay.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.datadisplay.R;
import com.android.datadisplay.adapter.SimpleTreeAdapter;
import com.android.datadisplay.bean.DeviceBean;
import com.android.datadisplay.bean.FileBean;
import com.android.datadisplay.bean.MonitorBean;
import com.android.datadisplay.bean.PLCBean;
import com.android.datadisplay.bean.ProjectBean;
import com.android.datadisplay.jdbc.MyDao;

import java.util.ArrayList;
import java.util.List;

public class LeftFragment extends BaseFragment {
    private ListView treeView;
    private ImageView iv_loading;
    private RelativeLayout rl_anim;
    private AnimationDrawable animDrawable;

    private List<ProjectBean> projectList;
    private List<MonitorBean> monitorList;
    private List<PLCBean> plcList;
    private List<DeviceBean> deviceList;
    private List<FileBean> mTreeData;
    private int index = 1;
    private SimpleTreeAdapter adapter;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.main_left, null);
        treeView = view.findViewById(R.id.lv_tree);
        iv_loading = view.findViewById(R.id.iv_anim);
        rl_anim = view.findViewById(R.id.rl_anim);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initData() {
        animDrawable = (AnimationDrawable) iv_loading.getBackground();
        new MyAsyncTask().execute();
    }
    private class MyAsyncTask extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            treeView.setVisibility(View.GONE);
            rl_anim.setVisibility(View.VISIBLE);
          /*  RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
            iv_loading.setLayoutParams(params);*/
            animDrawable.start();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            mTreeData = new ArrayList<>();
            int sourceTag = index;
             /*   Parcel parcel = Parcel.obtain();
                FileBean fileBean = FileBean.CREATOR.createFromParcel(parcel);*/

            mTreeData.add(new FileBean(index++, 0, "项目录"));
            projectList = MyDao.queryProject();
            if (projectList.size() != 0) {
                for (ProjectBean projectBean : projectList) {
                    int projectTag = index;
                    mTreeData.add(new FileBean(index++, sourceTag, projectBean.getProName(),projectBean));
                    monitorList = MyDao.queryMonitor(projectBean.getProId());
                    if (monitorList.size() != 0) {
                        for (MonitorBean monitorBean : monitorList) {
                            int monitorTag = index;
                            mTreeData.add(new FileBean(index++, projectTag, monitorBean.getMonitorName(),monitorBean));
                            plcList = MyDao.queryPLCByID(monitorBean.getMonitorID());
                            if (plcList.size() != 0) {
                                for (PLCBean plcBean : plcList) {
                                    int plcTag = index;
                                    mTreeData.add(new FileBean(index++, monitorTag, plcBean.getName(),plcBean));
                                    deviceList = MyDao.queryDeviceByID(plcBean.getPLCID());
                                    if (deviceList.size() != 0) {
                                        for (DeviceBean deviceBean : deviceList) {
                                            mTreeData.add(new FileBean(index++, plcTag, deviceBean.getDeviceName(),deviceBean));
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try {
                adapter = new SimpleTreeAdapter<FileBean>(treeView,mActivity,mTreeData,1);
                treeView.setAdapter(adapter);
                animDrawable.stop();
                rl_anim.setVisibility(View.GONE);
                treeView.setVisibility(View.VISIBLE);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
