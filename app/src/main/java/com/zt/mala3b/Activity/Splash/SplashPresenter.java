package com.zt.mala3b.Activity.Splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.CountDownTimer;

import com.zt.mala3b.Activity.Login.LoginActivity;
import com.zt.mala3b.Activity.Main.MainActivity;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ManagerData;
import com.zt.mala3b.Remote.ApiUtlis;
import com.zt.mala3b.Remote.UserService;
import com.zt.mala3b.SharedPackage.ClassesPackage.LanguageType;

import java.util.Locale;

public class SplashPresenter implements SplashViewPresenter {

    private String type,language;
    Context context ;
    private UserService userService ;

    public SplashPresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserService() ;
    }

    @Override
    public void setLanguages() {
        LanguageType languageType=new LanguageType();
        languageType.languageType = type;
        Configuration config = new Configuration();
        config.locale = new Locale(language);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public void ReadSharedPreference() {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        type=prefs.getString("type","arabic");
        language=prefs.getString("language","ar");
        boolean isLogin = prefs.getBoolean("isLogin",false);

        if (false)
        {
            getUser(prefs.getString("Token",""));
        }
        else {
            loading();
        }
    }

    @Override
    public void loading() {
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent mainActivity = new Intent(context, MainActivity.class);
                context.startActivity(mainActivity);
                ((Activity)context).finish();
            }
        }.start();
    }

    @Override
    public void getUser(String token) {

    }
}
