package com.zt.mala3b.Activity.Activation;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public interface ActivationViewPresenter {

    void  initView() ;
    void getData(Bundle bundle);
    void  setFocusText(EditText code1 , EditText code2, EditText code3, EditText code4);
    void  validate(EditText code1 , EditText code2, EditText code3, EditText code4);
    void callValidate() ;
    void setData(TextView textView) ;
}
