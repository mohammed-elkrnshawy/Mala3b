package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStadiumFeatureFragment extends Fragment {

    @BindView(R.id.recyleFeature) RecyclerView recyleFeature;

    private View view;


    public AddStadiumFeatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_stadium_feature, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

}
