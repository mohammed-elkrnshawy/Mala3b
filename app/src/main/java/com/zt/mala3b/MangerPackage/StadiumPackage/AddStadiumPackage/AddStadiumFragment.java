package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.mala3b.R;
import com.zt.mala3b.SharedPackage.Activities.MapsActivity;
import com.zt.mala3b.SharedPackage.ClassesPackage.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStadiumFragment extends Fragment implements AddStadiumInterface {

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

    private AddStadiumPresenter stadiumPresenter;
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
        initComponents();
        return view;
    }

    private void initComponents() {
        stadiumPresenter=new AddStadiumPresenter(this);
        stadiumPresenter.PrepareSpinners();
    }

    @OnClick({R.id.btnNext,R.id.stadiumAddress,R.id.stadiumPhoto}) void onFragmentClick(View view){
        switch (view.getId()){
            case R.id.btnNext:
                stadiumPresenter.validData();
                break;
            case R.id.stadiumAddress:
               stadiumPresenter.openMap();
            case R.id.stadiumPhoto:
                break;
        }
    }

    @Override
    public void onPrepareSpinners(ArrayAdapter<String> adapterSize) {
        spinnerSize.setAdapter(adapterSize);
    }

    @Override
    public void validData() {
        if(TextUtils.isEmpty(stadiumName.getText().toString().trim())){
            stadiumName.setError(getResources().getString(R.string.requiredField));
            stadiumName.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(stadiumAddress.getText().toString().trim())){
            stadiumAddress.setError(getResources().getString(R.string.requiredField));
            stadiumAddress.requestFocus();
            return;
        }

        if(spinnerSize.getSelectedItemPosition()==0){
            spinnerSize.requestFocus();
            return;
        }

        if(spinnerType.getSelectedItemPosition()==0){
            spinnerType.requestFocus();
            return;
        }

        stadiumPresenter.saveData();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== Constant.map&&resultCode== Activity.RESULT_OK)
            stadiumPresenter.onMapBack(requestCode, resultCode, data);
    }
}
