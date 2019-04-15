package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStadiumFeatureFragment extends Fragment {

    @BindView(R.id.recyleFeature) RecyclerView recyleFeature;
    @BindView(R.id.btnNext) Button btnNext;

    private View view;
    private AddStadiumFeaturePresenter featurePresenter;
    private int stadiumSize;
    private String stadiumName_AR,stadiumName_EN,stadiumAddress;
    private double stadiumLat,stadiumLng;


    public AddStadiumFeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_stadium_feature, container, false);
        ButterKnife.bind(this,view);
        initComponents();
        getIntentData();
        return view;
    }

    private void getIntentData() {
        Bundle bundle = getArguments();
        if (!bundle.isEmpty()) {
            stadiumSize = bundle.getInt("stadiumSize");
            stadiumAddress = bundle.getString("stadiumAddress");
            stadiumName_AR = bundle.getString("stadiumName_AR");
            stadiumName_EN = bundle.getString("stadiumName_EN");
            stadiumLat = bundle.getDouble("stadiumLat");
            stadiumLng = bundle.getDouble("stadiumLng");
        }
    }

    private void initComponents() {
        featurePresenter=new AddStadiumFeaturePresenter(this);
        featurePresenter.bindAdapter();
    }

    @OnClick({R.id.btnNext})void onClick(View view){
        switch (view.getId()){
            case R.id.btnNext:
                featurePresenter.addStadium(
                        stadiumSize,stadiumAddress,stadiumName_AR,stadiumName_EN,stadiumLat,stadiumLng
                );
                break;
        }
    }

}
