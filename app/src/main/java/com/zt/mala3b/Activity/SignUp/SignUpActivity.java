package com.zt.mala3b.Activity.SignUp;

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

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.txtInputName)
    TextInputLayout txtInputName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.txtInputEmail)
    TextInputLayout txtInputEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.txtInputPhone)
    TextInputLayout txtInputPhone;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.txtLogin)
    TextView txtLogin;

    SignUpPresenter mSignUpPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mSignUpPresenter = new SignUpPresenter(SignUpActivity.this) ;

    }

    @OnClick({R.id.btnRegister, R.id.txtLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                mSignUpPresenter.validate(txtInputName , edtName , txtInputEmail , edtEmail , txtInputPhone , edtPhone , txtInputPassword  ,edtPassword);
                break;
            case R.id.txtLogin:
                mSignUpPresenter.login();
                break;
        }
    }
}
