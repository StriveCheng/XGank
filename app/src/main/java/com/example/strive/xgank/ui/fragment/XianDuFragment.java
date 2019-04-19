package com.example.strive.xgank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.strive.xgank.R;
import com.example.strive.xgank.common.api.XianDuServices;
import com.example.strive.xgank.common.base.BaseFragment;
import com.example.strive.xgank.common.data.FirstCategoryInfo;
import com.example.strive.xgank.common.network.NetworkManger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xingcc on 2018/8/22.
 * main function  闲读
 *
 * @author strivecheng
 */

public class XianDuFragment extends BaseFragment {
    @BindView(R.id.xian_du_category_tab)
    TabLayout xianDuCategoryTab;
    @BindView(R.id.xian_du_category_vp)
    ViewPager xianDuCategoryVp;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xian_du, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static XianDuFragment newInstance(Bundle bundle) {
        XianDuFragment fragment = new XianDuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        xianDuCategoryTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        xianDuCategoryTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        XianDuServices xianDuServices = NetworkManger.getInstance().getRetrofit().create(XianDuServices.class);
        Call<ResponseBody> xianDu = xianDuServices.getFirstCategory ();
        xianDu.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                List<FirstCategoryInfo> categoryInfos = null;
//
                try {
                    String string = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        String results = jsonObject.getString("results");
                        Gson gson = new Gson();
                        categoryInfos =  gson.fromJson(results,new TypeToken<ArrayList<FirstCategoryInfo>>(){}.getType());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "onResponse: "+ string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (categoryInfos == null) {
                    return;
                }
                for (int i = 0; i < categoryInfos.size(); i++) {
                    xianDuCategoryTab.addTab(xianDuCategoryTab.newTab().setText(categoryInfos.get(i).getName()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + call.request().body() + "---" + t.getMessage());
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
