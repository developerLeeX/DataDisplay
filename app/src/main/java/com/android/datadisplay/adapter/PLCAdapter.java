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
import com.android.datadisplay.bean.PLCBean;
import com.android.datadisplay.fragment.DeviceFragment;

import java.util.List;

public class PLCAdapter extends BaseAdapter {
    private MainActivity main;
    private LayoutInflater inflater;
    private List<PLCBean> plcList;
    public PLCAdapter(Context context, List<PLCBean> data) {
        inflater =LayoutInflater.from(context);
        main = (MainActivity) context;
        plcList = data;
    }
    @Override
    public int getCount() {
        return plcList.size();
    }

    @Override
    public Object getItem(int position) {
        return plcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PLCBean plcBean = plcList.get(position);
        View view = inflater.inflate(R.layout.item_plc,null);
        RelativeLayout rl_toDevice = view.findViewById(R.id.rl_toDevice);
        TextView tv_plcName = view.findViewById(R.id.tv_plcName);
        TextView tv_plcCompany = view.findViewById(R.id.tv_plcCompany);
        TextView tv_plcModel = view.findViewById(R.id.tv_plcModel);
        TextView tv_plcIP = view.findViewById(R.id.tv_plcIP);
        TextView tv_plcPort = view.findViewById(R.id.tv_plcPort);
        TextView tv_protocol = view.findViewById(R.id.tv_protocol);
        TextView tv_plcRemark = view.findViewById(R.id.tv_plcRemark);
        rl_toDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.byID = plcBean.getPLCID();
                FragmentTransaction ft = main.fm.beginTransaction();
                //ft.replace(R.id.fl_main,new DeviceFragment(),main.TAG_DEVICE);
                Fragment fragment = main.fm.findFragmentByTag(main.TAG_FLAGS[3]);
                ft.hide(fragment);
                DeviceFragment deviceFragment = new DeviceFragment();
                Bundle bundle = new Bundle();
                bundle.putString(main.TAG_START,main.TAG_START_MAIN);
                deviceFragment.setArguments(bundle);
                ft.add(R.id.fl_main,deviceFragment,main.TAG_FLAGS[4]);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        tv_plcName.setText(plcBean.getName());
        tv_plcCompany.setText(plcBean.getCompany());
        tv_plcModel.setText(plcBean.getModel());
        tv_plcIP.setText(plcBean.getIP());
        tv_plcPort.setText(plcBean.getPort()+"");
        tv_protocol.setText(plcBean.getProtocol());
        tv_plcRemark.setText(plcBean.getRemark());

        return view;
    }
}