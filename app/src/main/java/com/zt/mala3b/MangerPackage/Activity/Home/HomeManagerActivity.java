package com.zt.mala3b.MangerPackage.Activity.Home;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeManagerActivity extends AppCompatActivity {

    HomeManagerPresenter managerPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);
        ButterKnife.bind(this);
        managerPresenter = new HomeManagerPresenter(HomeManagerActivity.this);
        managerPresenter.initView(navigation);
        managerPresenter.getData(getIntent().getExtras());
        managerPresenter.setToolbar(toolbar);
    }
}
