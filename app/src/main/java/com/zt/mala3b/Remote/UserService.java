package com.zt.mala3b.Remote;


import com.zt.mala3b.MangerPackage.Models.FeatureResponses.FeatureResponse;
import com.zt.mala3b.MangerPackage.Models.StadiumAddResponses.StadiumAddResponse;

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
    @GET("attribute")
    Call<FeatureResponse> getAttributes(
            @Header("X-localization") String Authorization
    );

    @Multipart
    @POST("manger/stadium")
    Call<StadiumAddResponse> addStadium(
            @Header("Authorization") String Authorization,
            @Query("size") String size
            , @Query("type") String type
            , @Query("has_lights") int has_lights
            , @Query("lat") double lat
            , @Query("lng") double lng
            , @Query("ar_name") String ar_name
            , @Query("en_name") String en_name
            , @Query("address") String address
            , @Query("city_id") int city_id
            , @Query("attributes[]") int attributes
            , @Part MultipartBody.Part mainImage
            , @Part MultipartBody.Part images
    );

}