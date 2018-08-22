package com.example.strive.xgank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.strive.xgank.R;
import com.example.strive.xgank.common.base.BaseFragment;

/**
 * Created by xingcc on 2018/8/22.
 * main function  我的
 *
 * @author strivecheng
 */

public class MyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

    public static MyFragment newInstance(Bundle bundle) {
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
