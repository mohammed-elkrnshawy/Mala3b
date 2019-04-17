package com.zt.mala3b.Activity.Activation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivationActivity extends AppCompatActivity {

    @BindView(R.id.Phone)
    TextView Phone;
    @BindView(R.id.code1)
    EditText code1;
    @BindView(R.id.code2)
    EditText code2;
    @BindView(R.id.code3)
    EditText code3;
    @BindView(R.id.code4)
    EditText code4;
    @BindView(R.id.L)
    LinearLayout L;
    @BindView(R.id.Resend)
    TextView Resend;
    @BindView(R.id.Confirm)
    Button Confirm;

    ActivationPresenter mActivationPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        ButterKnife.bind(this);
        code1.requestFocus();
        mActivationPresenter = new ActivationPresenter(ActivationActivity.this) ;
        mActivationPresenter.initView();
        mActivationPresenter.getData(getIntent().getExtras());
        mActivationPresenter.setData(Phone);
        mActivationPresenter.setFocusText(code1 , code2 , code3 , code4);
    }

    @OnClick({R.id.Resend, R.id.Confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Resend:
                break;
            case R.id.Confirm:
                mActivationPresenter.validate(code1 , code2 , code3 , code4);
                break;
        }
    }
}
