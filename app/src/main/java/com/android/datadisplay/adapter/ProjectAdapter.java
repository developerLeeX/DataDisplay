package com.android.datadisplay.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.activity.MainActivity;
import com.android.datadisplay.bean.ProjectBean;
import com.android.datadisplay.fragment.MonitorFragment;

import java.util.List;

public class ProjectAdapter extends BaseAdapter {
    private MainActivity main;
    private LayoutInflater inflater;
    private List<ProjectBean> projectList;
    public ProjectAdapter(Context context, List<ProjectBean> data) {
        inflater =LayoutInflater.from(context);
        main = (MainActivity) context;
        projectList = data;
    }
    @Override
    public int getCount() {
        return projectList.size();
    }

    @Override
    public Object getItem(int position) {
        return projectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ProjectBean projectBean = projectList.get(position);
        View view = inflater.inflate(R.layout.item_project, null);
        TextView tv_toMonitor = view.findViewById(R.id.tv_toMonitor);
        TextView tv_proName = view.findViewById(R.id.tv_proName);
        TextView tv_custName = view.findViewById(R.id.tv_custName);
        TextView tv_custContact = view.findViewById(R.id.tv_custContact);
        TextView tv_custPhone = view.findViewById(R.id.tv_cusetPhone);
        TextView tv_userName = view.findViewById(R.id.tv_userName);
        TextView tv_userContact = view.findViewById(R.id.tv_userContact);
        TextView tv_userPhone = view.findViewById(R.id.tv_userPhone);
        TextView tv_proInfo = view.findViewById(R.id.tv_proInfo);
        tv_toMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.byID = projectBean.getProId();
                FragmentTransaction ft = main.fm.beginTransaction();
                Fragment fragment = main.fm.findFragmentByTag(main.TAG_FLAGS[1]);
                ft.hide(fragment);
                MonitorFragment monitorFragment = new MonitorFragment();
                Bundle bundle = new Bundle();
                bundle.putString(main.TAG_START,main.TAG_START_MAIN);
                monitorFragment.setArguments(bundle);
                ft.add(R.id.fl_main,monitorFragment,main.TAG_FLAGS[2]);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        tv_proName.setText(projectBean.getProName());
        tv_custName.setText(projectBean.getCustName());
        tv_custContact.setText(projectBean.getCustContact());
        tv_custPhone.setText(projectBean.getCustPhone());
        tv_userName.setText(projectBean.getUserName());
        tv_userContact.setText(projectBean.getUserContact());
        tv_userPhone.setText(projectBean.getUserPhone());
        tv_proInfo.setText(projectBean.getProInfo());
        return view;
    }

}