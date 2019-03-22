package com.android.datadisplay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.datadisplay.activity.MainActivity;


public abstract class BaseFragment extends Fragment {
    public MainActivity main;
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        main = (MainActivity) mActivity;
        main.runFragmentCount++;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = initView();
         return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume","onResume");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart","onStart");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        main.runFragmentCount--;
        Log.i("onDestroy","onDestroy");
    }

    protected abstract View initView();
    public abstract void initData();
}
