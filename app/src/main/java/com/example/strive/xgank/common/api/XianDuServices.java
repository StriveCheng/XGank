package com.example.strive.xgank.common.api;

import com.example.strive.xgank.common.data.FirstCategoryInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by strive on 2018/8/22.
 */

public interface XianDuServices {

    @GET("xiandu/categories")
    Call<ResponseBody> getFirstCategory();

}
