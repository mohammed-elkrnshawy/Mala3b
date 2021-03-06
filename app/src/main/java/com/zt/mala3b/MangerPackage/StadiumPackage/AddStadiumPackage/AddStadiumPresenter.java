package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.zt.mala3b.R;
import com.zt.mala3b.SharedPackage.Activities.MapsActivity;
import com.zt.mala3b.SharedPackage.ClassesPackage.CameraHelper;
import com.zt.mala3b.SharedPackage.ClassesPackage.Constant;


public class AddStadiumPresenter {

    private AddStadiumFragment view;
    private double lat,lng;

    public AddStadiumPresenter(AddStadiumFragment addStadiumFragment) {
        this.view=addStadiumFragment;
    }

    public void PrepareSpinners(){
        ArrayAdapter<String> adapterSize = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item,view.getResources().getStringArray(R.array.staduim_size));
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item,view.getResources().getStringArray(R.array.staduim_type));
        view.onPrepareSpinners(adapterSize,adapterType);
        view.onPrepareRecycle();
    }

    public void openMap() {
        Intent intentMap=new Intent(view.getContext(), MapsActivity.class);
        view.startActivityForResult(intentMap, Constant.map);
    }

    public void onMapBack(int requestCode, int resultCode, Intent data) {
            lat = data.getDoubleExtra("lat",0);
            lng = data.getDoubleExtra("lng",0);
            String address = data.getStringExtra("address");
            view.stadiumAddress.setText(address);
    }

    public void validData() {
        view.validData();
    }

    public void saveData() {
        view.saveData(lat,lng);
    }
}
