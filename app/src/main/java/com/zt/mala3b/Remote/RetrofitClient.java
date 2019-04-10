package com.zt.mala3b.Remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mahmoud on 11/11/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit=null;



    public static Retrofit getClient(String Url) {

        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(setTime())
                    .build();

        }
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        RetrofitClient.retrofit = retrofit;
    }

    private static OkHttpClient setTime() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        return  okHttpClient;
    }
}
