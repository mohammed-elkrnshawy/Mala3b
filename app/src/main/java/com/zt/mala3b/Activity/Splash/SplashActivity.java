package com.zt.mala3b.Activity.Splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zt.mala3b.R;

public class SplashActivity extends AppCompatActivity {

    SplashPresenter mSplashPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(SplashActivity.this) ;
        mSplashPresenter.ReadSharedPreference();
        mSplashPresenter.setLanguages();
    }
}
