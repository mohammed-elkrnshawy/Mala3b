package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;

import android.widget.ArrayAdapter;

public interface AddStadiumInterface {
    void onPrepareRecycle();
    void onPrepareSpinners(ArrayAdapter<String> adapterSize,ArrayAdapter<String> adapterType);
    void validData();
    void saveData(double lat,double lng);
}
