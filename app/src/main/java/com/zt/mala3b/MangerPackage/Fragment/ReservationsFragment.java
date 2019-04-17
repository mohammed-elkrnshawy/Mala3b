package com.zt.mala3b.MangerPackage.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zt.mala3b.MangerPackage.Activity.Home.HomeManagerPresenter;
import com.zt.mala3b.R;

public class ReservationsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations , container , false);
        return view;    }

    @Override
    public void onResume() {
        super.onResume();
        HomeManagerPresenter.AddStadium.setVisibility(View.GONE);
        HomeManagerPresenter.EditProfile.setVisibility(View.GONE);
    }
}
