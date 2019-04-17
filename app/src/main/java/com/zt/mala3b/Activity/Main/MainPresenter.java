package com.zt.mala3b.Activity.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zt.mala3b.Activity.Login.LoginActivity;
import com.zt.mala3b.Activity.SignUp.SignUpActivity;

public class MainPresenter implements MainViewPresenter {


    Context context ;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void goToRegister() {
        Intent intent = new Intent(context , SignUpActivity.class) ;
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(context , LoginActivity.class) ;
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
