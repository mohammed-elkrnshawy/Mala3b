package com.zt.mala3b.Activity.Splash;

import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ManagerData;

public interface SplashViewPresenter {
    void setLanguages();
    void ReadSharedPreference();
    void loading();
    void getUser(String token);

}
