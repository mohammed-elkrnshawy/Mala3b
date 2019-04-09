package com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import com.zt.mala3b.MangerPackage.Adapters.PhotoAddAdapter;
import com.zt.mala3b.R;
import com.zt.mala3b.SharedPackage.Activities.MapsActivity;
import com.zt.mala3b.SharedPackage.ClassesPackage.CameraHelper;
import com.zt.mala3b.SharedPackage.ClassesPackage.Constant;

import java.util.ArrayList;
import java.util.List;

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

    private AddStadiumFeatureFragment featureFragment;
    private boolean isMainPhoto=false;
    private CameraHelper cameraHelper;
    private AddStadiumPresenter stadiumPresenter;
    private PhotoAddAdapter photoAddAdapter;
    private Bitmap mainPhoto;
    private List<Bitmap>  bitmapList;
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
        cameraHelper=new CameraHelper(this);
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
               break;
            case R.id.stadiumPhoto:
                isMainPhoto=true;
                cameraHelper.SelectPhotoDialog();
                break;
        }
    }

    @Override
    public void onPrepareRecycle() {
        Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_photo);
        bitmapList=new ArrayList<>();
        bitmapList.add(icon);
        photoAddAdapter=new PhotoAddAdapter(bitmapList,getContext(),cameraHelper);
        recylePhotos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recylePhotos.setAdapter(photoAddAdapter);
    }

    @Override
    public void onPrepareSpinners(ArrayAdapter<String> adapterSize) {
        spinnerSize.setAdapter(adapterSize);
    }

    @Override
    public void validData() {

        if (mainPhoto==null){
            stadiumPhoto.requestFocus();
            Toast.makeText(getContext(), getContext().getString(R.string.select_photo), Toast.LENGTH_SHORT).show();
            return;
        }

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


        stadiumPresenter.saveData();

    }

    @Override
    public void saveData() {
        featureFragment=new AddStadiumFeatureFragment();
        setFragment(featureFragment,getString(R.string.feature));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== Constant.map&&resultCode== Activity.RESULT_OK)
            stadiumPresenter.onMapBack(requestCode, resultCode, data);
        else if ((requestCode==Constant.Camera||requestCode==Constant.Gallery)&&resultCode==Activity.RESULT_OK){
            if (isMainPhoto){
                mainPhoto=cameraHelper.onResult(requestCode,data);
                stadiumPhoto.setImageBitmap(mainPhoto);
                isMainPhoto=false;
            }else {
                bitmapList.add(cameraHelper.onResult(requestCode,data));
                photoAddAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setFragment(Fragment fragment, String Title) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.stadium_container, fragment).addToBackStack(Title)
                .commitAllowingStateLoss();
    }
}
