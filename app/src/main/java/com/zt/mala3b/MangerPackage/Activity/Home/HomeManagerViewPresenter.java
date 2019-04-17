package com.zt.mala3b.MangerPackage.Activity.Home;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public interface HomeManagerViewPresenter {

    void initView(BottomNavigationView navigationView) ;
    void getData(Bundle bundle) ;
    void setToolbar(Toolbar toolbar);
    void setFragment(Fragment fragment , String Title,Bundle bundle) ;

}
