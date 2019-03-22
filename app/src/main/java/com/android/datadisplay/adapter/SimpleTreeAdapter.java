package com.android.datadisplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.datadisplay.R;
import com.android.datadisplay.activity.MainActivity;
import com.android.datadisplay.bean.Node;
import com.android.datadisplay.fragment.DeviceFragment;
import com.android.datadisplay.fragment.MonitorFragment;
import com.android.datadisplay.fragment.PLCFragment;
import com.android.datadisplay.fragment.ProjectFragment;

import java.util.List;


public class SimpleTreeAdapter<FileBean> extends TreeListViewAdapter {
    private Activity mActivity;

    public SimpleTreeAdapter(ListView mTree, Context context, List datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
        mActivity = (Activity) context;
    }

    @Override
    public View getConvertView(final Node node, final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.ib_next_tree);
            viewHolder.icon = (ImageView) convertView
                    .findViewById(R.id.id_treenode_icon);
            viewHolder.label = (TextView) convertView
                    .findViewById(R.id.id_treenode_label);
            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1)
        {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else
        {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        if (node.getLevel()==0){
            viewHolder.imageView.setVisibility(View.GONE);
        }else {
            viewHolder.imageView.setVisibility(View.VISIBLE);
        }
        viewHolder.label.setText(node.getName());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity) mActivity;
                FragmentManager fm = main.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(main.getmCurrentFragment());
                Bundle bundle = new Bundle();
                bundle.putString(main.TAG_START,main.TAG_START_LEFT);
                switch (node.getLevel()){
                    case 0:

                        break;
                    case 1:
                        ProjectFragment projectFragment = new ProjectFragment();
                        bundle.putSerializable("data",node.getBaseBean());
                        projectFragment.setArguments(bundle);
                        ft.add(R.id.fl_main,projectFragment,main.TAG_FLAGS[1]);
                        break;
                    case 2:
                        MonitorFragment monitorFragment = new MonitorFragment();
                        bundle.putSerializable("data",node.getBaseBean());
                        monitorFragment.setArguments(bundle);
                        ft.add(R.id.fl_main,monitorFragment,main.TAG_FLAGS[2]);
                        break;
                    case 3:
                        PLCFragment plcFragment = new PLCFragment();
                        bundle.putSerializable("data",node.getBaseBean());
                        plcFragment.setArguments(bundle);
                        ft.add(R.id.fl_main,plcFragment,main.TAG_FLAGS[3]);
                        break;
                    case 4:
                        DeviceFragment deviceFragment = new DeviceFragment();
                        bundle.putSerializable("data",node.getBaseBean());
                        deviceFragment.setArguments(bundle);
                        ft.add(R.id.fl_main,deviceFragment,main.TAG_FLAGS[4]);
                        break;
                }
                ft.addToBackStack(null);
                ft.commit();
                main.toggle();
              //  ToastUtil.toast2UI(mActivity,"条目"+position+"被点击");
            }
        });

        return convertView;
    }
    private final class ViewHolder
    {
        ImageView icon;
        TextView label;
        ImageView imageView;
    }

}
