package com.zt.mala3b.Activity.SelectRole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zt.mala3b.Activity.Main.MainActivity;
import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectRoleActivity extends AppCompatActivity {

    @BindView(R.id.linearStadium)
    LinearLayout linearStadium;
    @BindView(R.id.linearPlayer)
    LinearLayout linearPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.linearPlayer,R.id.linearStadium}) void clicked(View view){
        switch (view.getId()){
            case R.id.linearPlayer:
                Toast.makeText(this, "Not Availabe Now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linearStadium:
                Intent intent=new Intent(SelectRoleActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
