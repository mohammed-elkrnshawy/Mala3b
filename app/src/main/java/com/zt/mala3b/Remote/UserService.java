package com.zt.mala3b.Remote;


import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ResponseManagerData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/register/Manger")
    Call<ResponseManagerData> registerManager(
            @Query("name") String name ,
            @Query("phone") String phone ,
            @Query("email") String email ,
            @Query("password") String password ,
            @Query("fcm_token_android") String fcm_token_android
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/login/Manger")
    Call<ResponseManagerData> loginManager(
            @Query("phone") String phone ,
            @Query("password") String password ,
            @Query("fcm_token_android") String fcm_token_android
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/profile/Manger")
    Call<ResponseManagerData> getManagerData(
            @Header("Authorization") String Authorization
    );

}