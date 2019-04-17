package com.zt.mala3b.Activity.SignUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.zt.mala3b.Activity.Activation.ActivationActivity;
import com.zt.mala3b.Activity.Login.LoginActivity;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ResponseManagerData;
import com.zt.mala3b.R;
import com.zt.mala3b.Remote.ApiUtlis;
import com.zt.mala3b.Remote.UserService;
import com.zt.mala3b.SharedPackage.ClassesPackage.SharedClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements SignUpViewPresenter {

    private Context context ;
    private UserService userService ;
    public SignUpPresenter(Context context) {
        this.context = context ;
        userService = ApiUtlis.getUserService() ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void callRegister(String name, String email, String phone, String password , String fcm) {
        SharedClass.ShowWaiting(context);
        Call<ResponseManagerData> call = userService.registerManager(name , phone , email  , password , fcm) ;
        call.enqueue(new Callback<ResponseManagerData>() {
            @Override
            public void onResponse(Call<ResponseManagerData> call, Response<ResponseManagerData> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        SharedClass.hideWaiting();
                        activate(response.body().getData().getPhone());
                    }else {
                        SharedClass.hideWaiting();
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    SharedClass.hideWaiting();
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseManagerData> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void validate(TextInputLayout inputName, EditText edtName, TextInputLayout inputEmail, EditText edtEmail, TextInputLayout inputPhone, EditText edtPhone, TextInputLayout inputPass, EditText edtPass) {
        String name = edtName.getText().toString().trim() ;
        String email = edtEmail.getText().toString().trim() ;
        String phone = edtPhone.getText().toString().trim() ;
        String password = edtPass.getText().toString().trim() ;

        if (TextUtils.isEmpty(name)) {
            inputName.setError(context.getString(R.string.required_field));
            setAnimation(inputName);
            return;
        }

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(context.getString(R.string.required_field));
            setAnimation(inputEmail);
            return;
        }

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

        callRegister(name , email , phone , password , "fcm");

    }

    @Override
    public void setAnimation(TextInputLayout input) {
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(0)
                .playOn(input);
    }

    @Override
    public void login() {
        Intent intent = new Intent(context , LoginActivity.class) ;
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    public void activate(String phone) {
        Intent intent = new Intent(context , ActivationActivity.class) ;
        intent.putExtra("phone" , phone) ;
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
