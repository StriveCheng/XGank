package com.example.strive.xgank.common.network;

import com.example.strive.xgank.common.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xingcc on 2019-04-18.
 * main function
 * 网络封装类
 *
 * @author strivecheng
 */
public class NetworkManger {
    private static NetworkManger mInstance;
    private Retrofit mRetrofit;

    public static NetworkManger getInstance() {
        if (mInstance == null) {
            synchronized (NetworkManger.class) {
                if (mInstance == null) {
                    mInstance = new NetworkManger();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_GANK_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
       return  mRetrofit;
    }
}
