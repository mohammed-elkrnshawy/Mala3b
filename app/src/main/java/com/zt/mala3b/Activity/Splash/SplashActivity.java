package com.zt.mala3b.Activity.Splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zt.mala3b.Activity.SelectRole.SelectRoleActivity;
import com.zt.mala3b.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent=new Intent(SplashActivity.this, SelectRoleActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();


    }
}
