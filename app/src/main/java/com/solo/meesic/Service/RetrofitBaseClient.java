package com.solo.meesic.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBaseClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String base_url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                        .readTimeout(10000, TimeUnit.MILLISECONDS)
                                        .writeTimeout(10000, TimeUnit.MILLISECONDS)
                                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                                        .retryOnConnectionFailure(true)
                                        .build();
        retrofit = new Retrofit.Builder()
                        .baseUrl(base_url)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        return retrofit;
    }
}
