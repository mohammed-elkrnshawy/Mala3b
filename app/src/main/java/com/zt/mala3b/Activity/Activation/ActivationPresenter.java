package com.zt.mala3b.Activity.Activation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.mala3b.Activity.Login.LoginActivity;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ManagerData;
import com.zt.mala3b.R;
import com.zt.mala3b.Remote.ApiUtlis;
import com.zt.mala3b.Remote.UserService;

public class ActivationPresenter implements ActivationViewPresenter {

    private StringBuilder sb;
    Context context ;
    String phone, token, code;
    private UserService userService;
    private boolean isChange ;
    private ManagerData managerData  ;


    public ActivationPresenter(Context context) {
        this.context = context ;
        userService = ApiUtlis.getUserService() ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            phone =  bundle.getString("phone");

        }
    }

    @Override
    public void setData(TextView textView) {
        textView.setText(phone);
    }

    @Override
    public void setFocusText(final EditText Code1, final EditText Code2, final EditText Code3, final EditText Code4) {
        sb = new StringBuilder();

        Code1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (sb.length() == 0 & Code1.length() == 1) {
                    sb.append(s);
                    Code1.clearFocus();
                    Code2.requestFocus();
                    Code3.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (sb.length() == 1) {

                    sb.deleteCharAt(0);

                }

            }

            public void afterTextChanged(Editable s) {
                if (sb.length() == 0) {

                    Code1.requestFocus();
                }

            }
        });

        Code2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (sb.length() == 0 & Code2.length() == 1) {
                    sb.append(s);
                    Code2.clearFocus();
                    Code3.requestFocus();
                    Code3.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (sb.length() == 1) {

                    sb.deleteCharAt(0);

                }

            }

            public void afterTextChanged(Editable s) {
                if (sb.length() == 0) {

                    Code2.requestFocus();
                }

            }
        });

        Code3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (sb.length() == 0 & Code2.length() == 1) {
                    sb.append(s);
                    Code3.clearFocus();
                    Code4.requestFocus();
                    Code4.setCursorVisible(true);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

                if (sb.length() == 1) {

                    sb.deleteCharAt(0);

                }

            }

            public void afterTextChanged(Editable s) {
                if (sb.length() == 0) {

                    Code3.requestFocus();
                }

            }
        });
        Code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Code4.length() == 0) {
                    Code4.requestFocus();
                }
                if (s.toString().length() > 1)
                    Code4.setText(s.toString().substring(0, 1));
                code = Code1.getText().toString().trim()
                        + Code2.getText().toString().trim()
                        + Code3.getText().toString().trim()
                        + Code4.getText().toString().trim();
                //if(code.length() == 4)
                //    sendCode();
            }
        });
    }

    @Override
    public void validate(EditText Code1, EditText Code2, EditText Code3, EditText Code4) {
        if (TextUtils.isEmpty(Code1.getText().toString()) || TextUtils.isEmpty(Code2.getText().toString()) || TextUtils.isEmpty(Code3.getText().toString()) || TextUtils.isEmpty(Code4.getText().toString()) ){
            Toast.makeText(context , context.getString(R.string.enter_validate), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context , LoginActivity.class) ;
        context.startActivity(intent);
        ((Activity)context).finishAffinity();
    }

    @Override
    public void callValidate() {

    }
}
