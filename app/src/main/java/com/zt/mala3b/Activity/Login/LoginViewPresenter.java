package com.zt.mala3b.Activity.Login;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public interface LoginViewPresenter {

    void callLogin(String phone , String password , String fcm) ;
    void validate(TextInputLayout inputEmail , EditText edtEmail , TextInputLayout inputPass , EditText edtPass ) ;
    void setAnimation(TextInputLayout input) ;

    void goToRegister();
    void goToForgetPassword();
    void storeDataInShared(String token);

}
