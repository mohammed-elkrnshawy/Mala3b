package com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ManagerData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("is_confirmed")
    @Expose
    private String isConfirmed;
    @SerializedName("fcm_token_android")
    @Expose
    private String fcmTokenAndroid;
    @SerializedName("fcm_token_ios")
    @Expose
    private String fcmTokenIos;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getFcmTokenAndroid() {
        return fcmTokenAndroid;
    }

    public void setFcmTokenAndroid(String fcmTokenAndroid) {
        this.fcmTokenAndroid = fcmTokenAndroid;
    }

    public String getFcmTokenIos() {
        return fcmTokenIos;
    }

    public void setFcmTokenIos(String fcmTokenIos) {
        this.fcmTokenIos = fcmTokenIos;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}