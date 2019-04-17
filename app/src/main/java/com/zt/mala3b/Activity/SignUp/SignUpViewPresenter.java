package com.zt.mala3b.Activity.SignUp;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public interface SignUpViewPresenter {

    void initView();

    void callRegister(String name , String email , String phone , String password , String fcm);

    void validate(TextInputLayout inputName , EditText edtName , TextInputLayout inputEmail , EditText edtEmail ,
                  TextInputLayout inputPhone , EditText edtPhone , TextInputLayout inputPass , EditText edtPass);

    void setAnimation(TextInputLayout input) ;

    void login();

    void activate(String phone);

}
