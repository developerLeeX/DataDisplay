package com.android.datadisplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.activity.MainActivity;
import com.android.datadisplay.bean.DeviceBean;

import java.util.List;


public class DeviceAdapter extends BaseAdapter {
    private MainActivity main;
    private LayoutInflater inflater;
    private List<DeviceBean> deviceList;
    public DeviceAdapter(Context context, List<DeviceBean> data) {
        inflater =LayoutInflater.from(context);
        main = (MainActivity) context;
        deviceList = data;
    }
    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DeviceBean deviceBean = deviceList.get(position);
        View view =inflater.inflate(R.layout.item_device,null);
        TextView tv_deviceName = view.findViewById(R.id.tv_deviceName);
        TextView tv_type = view.findViewById(R.id.tv_deviceDType);
        TextView tv_deviceCompany = view.findViewById(R.id.tv_deviceCompany);
        TextView tv_deviceModel = view.findViewById(R.id.tv_deviceModel);
        TextView tv_deviceAttribute = view.findViewById(R.id.tv_deviceAttribute);
        TextView tv_deviceRemark = view.findViewById(R.id.tv_deviceRemark);

        tv_deviceName.setText(deviceBean.getDeviceName());
        tv_deviceCompany.setText(deviceBean.getCompany());
        tv_deviceModel.setText(deviceBean.getModel());
        tv_deviceAttribute.setText(deviceBean.getAttribute());
        tv_deviceRemark.setText(deviceBean.getRemark()+"");
        tv_type.setText(deviceBean.getDType());

        return view;
    }
}