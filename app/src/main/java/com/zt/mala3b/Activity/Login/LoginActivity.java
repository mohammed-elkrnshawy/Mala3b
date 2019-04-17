package com.zt.mala3b.Activity.Login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.txtInputEmail)
    TextInputLayout txtInputEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtForget)
    TextView txtForget;
    @BindView(R.id.txtRegister)
    TextView txtRegister;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(LoginActivity.this);

    }

    @OnClick({R.id.btnLogin, R.id.txtForget, R.id.txtRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                mLoginPresenter.validate(txtInputEmail , edtEmail ,txtInputPassword ,edtPassword );
                break;
            case R.id.txtForget:
                mLoginPresenter.goToForgetPassword();
                break;
            case R.id.txtRegister:
                mLoginPresenter.goToRegister();
                break;
        }
    }
}
