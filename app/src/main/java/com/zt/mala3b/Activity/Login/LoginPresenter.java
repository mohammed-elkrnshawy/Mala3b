package com.zt.mala3b.Activity.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.zt.mala3b.Activity.ForgetPassword.ForgetPasswordActivity;
import com.zt.mala3b.Activity.SignUp.SignUpActivity;
import com.zt.mala3b.MangerPackage.Activity.Home.HomeManagerActivity;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ManagerData;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ResponseManagerData;
import com.zt.mala3b.R;
import com.zt.mala3b.Remote.ApiUtlis;
import com.zt.mala3b.Remote.UserService;
import com.zt.mala3b.SharedPackage.ClassesPackage.SharedClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginViewPresenter {

    private Context context ;
    private UserService userService ;
    private ManagerData managerData ;

    public LoginPresenter(Context context) {
        this.context = context;
        userService = ApiUtlis.getUserService() ;
    }

    @Override
    public void callLogin(String phone, String password , String fcm) {
        SharedClass.ShowWaiting(context);
        Call<ResponseManagerData> call = userService.loginManager(phone , password , fcm) ;
        call.enqueue(new Callback<ResponseManagerData>() {
            @Override
            public void onResponse(Call<ResponseManagerData> call, Response<ResponseManagerData> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        SharedClass.hideWaiting();
                        storeDataInShared(response.body().getData().getToken());
                        Intent intent = new Intent(context , HomeManagerActivity.class) ;
                        intent.putExtra("data" , response.body().getData()) ;
                        context.startActivity(intent);
                        ((Activity)context).finishAffinity();
                    }else {
                        SharedClass.hideWaiting();
                        Toast.makeText(context, response.body().getMsg() , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    SharedClass.hideWaiting();
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseManagerData> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void validate(TextInputLayout inputPhone, EditText edtPhone, TextInputLayout inputPass, EditText edtPass) {
        String phone = edtPhone.getText().toString().trim() ;
        String password = edtPass.getText().toString().trim() ;

        if (TextUtils.isEmpty(phone)) {
            inputPhone.setError(context.getString(R.string.required_field));
            setAnimation(inputPhone);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            inputPass.setError(context.getString(R.string.required_field));
            setAnimation(inputPass);
            return;
        }
        callLogin(phone , password , "fcm");
    }

    @Override
    public void setAnimation(TextInputLayout input) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(0)
                .playOn(input);
    }

    @Override
    public void goToRegister() {
        Intent intent = new Intent(context , SignUpActivity.class) ;
        context.startActivity(intent);
        //((Activity)context).finish();
    }

    @Override
    public void goToForgetPassword() {
        Intent intent = new Intent(context , ForgetPasswordActivity.class) ;
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    public void storeDataInShared(String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE).edit();
        editor.putBoolean("isLogin", true);
        editor.putString("Token", token);
        editor.apply();
    }
}
