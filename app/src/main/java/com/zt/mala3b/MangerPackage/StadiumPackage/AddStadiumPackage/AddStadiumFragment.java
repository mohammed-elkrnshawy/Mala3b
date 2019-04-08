package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zt.mala3b.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStadiumFragment extends Fragment {

    @BindView(R.id.stadiumPhoto)
    ImageView stadiumPhoto;
    @BindView(R.id.recylePhotos)
    RecyclerView recylePhotos;
    @BindView(R.id.stadiumName)
    EditText stadiumName;
    @BindView(R.id.stadiumAddress)
    TextView stadiumAddress;
    @BindView(R.id.spinnerSize)
    Spinner spinnerSize;
    @BindView(R.id.spinnerType) Spinner spinnerType;
    @BindView(R.id.btnNext)
    Button btnNext;

    private View view;

    public AddStadiumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_stadium, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick({R.id.btnNext,R.id.stadiumAddress,R.id.stadiumPhoto}) void onFragmentClick(View view){
        switch (view.getId()){
            case R.id.btnNext:
                break;
            case R.id.stadiumAddress:
                break;
            case R.id.stadiumPhoto:
                break;
        }
    }

}
