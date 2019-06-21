package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddStadiumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stadium);
        setFragment(new AddStadiumFragment(),getString(R.string.add_stadium));
    }

    private void setFragment(Fragment fragment, String Title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.stadium_container, fragment)
                .addToBackStack(Title).commit();
    }

}
