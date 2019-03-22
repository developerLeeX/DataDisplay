package com.android.datadisplay.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.activity.MainActivity;
import com.android.datadisplay.bean.MonitorBean;
import com.android.datadisplay.fragment.PLCFragment;

import java.util.List;

public class MonitorAdapter extends BaseAdapter {
    private MainActivity main;
    private LayoutInflater inflater;
    private List<MonitorBean> monitorList;
    public MonitorAdapter(Context context, List<MonitorBean> data) {
        inflater =LayoutInflater.from(context);
        main = (MainActivity) context;
        monitorList = data;
    }
    @Override
    public int getCount() {
        return monitorList.size();
    }

    @Override
    public Object getItem(int position) {
        return monitorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MonitorBean monitorBean = monitorList.get(position);
        View view = inflater.inflate(R.layout.item_monitor,null);
        RelativeLayout rl_toPLC = view.findViewById(R.id.rl_toPLC);
        TextView tv_monitorName = view.findViewById(R.id.tv_monitorName);
        TextView tv_monitorNum = view.findViewById(R.id.tv_monitorNum);
        TextView tv_productName = view.findViewById(R.id.tv_productName);
        TextView tv_productSize = view.findViewById(R.id.tv_productSize);
        TextView tv_province = view.findViewById(R.id.tv_province);
        TextView tv_city = view.findViewById(R.id.tv_city);
        TextView tv_district = view.findViewById(R.id.tv_district);
        TextView tv_lng = view.findViewById(R.id.tv_lng);
        TextView tv_lat = view.findViewById(R.id.tv_lat);
        TextView tv_address = view.findViewById(R.id.tv_address);
        TextView tv_remark = view.findViewById(R.id.tv_remark);
        rl_toPLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.byID = monitorBean.getMonitorID();
                FragmentTransaction ft = main.fm.beginTransaction();
                //        ft.replace(R.id.fl_main,new PLCFragment(),main.TAG_PLC);
                Fragment fragment = main.fm.findFragmentByTag(main.TAG_FLAGS[2]);
                ft.hide(fragment);
                PLCFragment plcFragment = new PLCFragment();
                Bundle bundle = new Bundle();
                bundle.putString(main.TAG_START,main.TAG_START_MAIN);
                plcFragment.setArguments(bundle);
                ft.add(R.id.fl_main,plcFragment,main.TAG_FLAGS[3]);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        tv_monitorName.setText(monitorBean.getMonitorName());
        tv_monitorNum.setText(monitorBean.getMonitorNum());
        tv_productName.setText(monitorBean.getProductName());
        tv_productSize.setText(monitorBean.getProductSize());
        tv_province.setText(monitorBean.getProvince());
        tv_city.setText(monitorBean.getCity());
        tv_district.setText(monitorBean.getDistrict());
        tv_lng.setText(monitorBean.getLng());
        tv_lat.setText(monitorBean.getLat());
        tv_address.setText(monitorBean.getAddress());
        tv_remark.setText(monitorBean.getRemark());
        return view;
    }
}
