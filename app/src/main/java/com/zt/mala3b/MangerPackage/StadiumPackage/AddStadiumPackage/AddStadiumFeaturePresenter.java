package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.zt.mala3b.MangerPackage.Adapters.FeatureAdapter;
import com.zt.mala3b.MangerPackage.Models.FeatureResponses.Attribute;
import com.zt.mala3b.MangerPackage.Models.FeatureResponses.FeatureResponse;
import com.zt.mala3b.MangerPackage.Models.StadiumAddResponses.StadiumAddResponse;
import com.zt.mala3b.Remote.ApiUtlis;
import com.zt.mala3b.Remote.UserService;
import com.zt.mala3b.SharedPackage.ClassesPackage.SharedData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStadiumFeaturePresenter {

    private File file;
    private MultipartBody.Part body;
    private UserService userService;
    private Map<String, Integer> featureMap=new HashMap<String, Integer>();
    private List<Attribute> attributeList=new ArrayList<>();
    private FeatureAdapter featureAdapter;
    private LinearLayoutManager linearLayoutManager;

    private AddStadiumFeatureFragment view;
    private Context context;

    public AddStadiumFeaturePresenter(AddStadiumFeatureFragment view){
        this.view=view;
        context=view.getContext();
        userService= ApiUtlis.getUserService();
    }

    public void bindAdapter() {
        linearLayoutManager=new LinearLayoutManager(context);
        featureAdapter=new FeatureAdapter(attributeList,context,featureMap);
        view.recyleFeature.setLayoutManager(linearLayoutManager);
        view.recyleFeature.setAdapter(featureAdapter);
        callAttributes();
    }

    private void callAttributes(){
        Call<FeatureResponse> call=userService.getAttributes("en");
        call.enqueue(new Callback<FeatureResponse>() {
            @Override
            public void onResponse(Call<FeatureResponse> call, Response<FeatureResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        attributeList.addAll(response.body().getData().getAttributes());
                        featureAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeatureResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private MultipartBody.Part persistImage(Bitmap mBitmap, String name) {
        File filesDir = context.getFilesDir();
        file = new File(filesDir, name + ".jpg");
        OutputStream os;
        try {

            os = new FileOutputStream(file);
            Log.i("sadsad", mBitmap.getByteCount() + "");

            mBitmap.compress(Bitmap.CompressFormat.JPEG, 30, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Toast.makeText(context, "select photo", Toast.LENGTH_SHORT).show();
            Log.e("Error writing bitmap", e.getMessage());
        }
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        body = MultipartBody.Part.createFormData(name, file.getName(), requestFile);

        return body;
    }

    public void addStadium(int stadiumSize, String stadiumAddress, String stadiumName_AR, String stadiumName_EN, double stadiumLat, double stadiumLng) {
        Call<StadiumAddResponse> call=userService.addStadium(
                "Bearer "+ SharedData.token
                ,stadiumSize+"","grass",1,stadiumLat,stadiumLng,stadiumName_AR,stadiumName_EN
                ,stadiumAddress,1,1,persistImage(SharedData.bitmapStadiumMain,"image")
                ,persistImage(SharedData.bitmapStadiumMain,"images[]")
        );

        call.enqueue(new Callback<StadiumAddResponse>() {
            @Override
            public void onResponse(Call<StadiumAddResponse> call, Response<StadiumAddResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()){
                        Toast.makeText(context, response.body().getData().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StadiumAddResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
