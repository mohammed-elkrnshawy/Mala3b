package com.zt.mala3b.Activity.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zt.mala3b.Activity.Login.LoginActivity;
import com.zt.mala3b.Activity.SignUp.SignUpActivity;
import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    MainPresenter mainPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(MainActivity.this) ;
    }

    @OnClick({R.id.btnRegister, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                mainPresenter.goToRegister();
                break;
            case R.id.btnLogin:
                mainPresenter.goToLogin();
        }

    }
   /* @OnClick({R.id.btnLogin,R.id.btnRegister})
        void Clicked(View view){
        switch (view.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;

            case R.id.btnRegister:
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                break;
        }
    }*/



}
