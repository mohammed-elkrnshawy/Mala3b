package com.zt.mala3b.MangerPackage.Activity.Home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.mala3b.MangerPackage.Fragment.ProfileFragment;
import com.zt.mala3b.MangerPackage.Fragment.ReservationsFragment;
import com.zt.mala3b.MangerPackage.Moduls.ResponseManagerData.ManagerData;
import com.zt.mala3b.MangerPackage.StadiumPackage.AddStadiumPackage.AddStadiumFragment;
import com.zt.mala3b.R;

public class HomeManagerPresenter implements HomeManagerViewPresenter {

    private Context context ;
    private TextView txtTitle ;
    public static ImageView EditProfile , AddStadium ;
    private String preFragmentName = "";
    private ManagerData managerData ;

    public HomeManagerPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void initView(BottomNavigationView navigation) {
        navigation.setSelectedItemId(R.id.reservation);
        navigation.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            managerData =  (ManagerData) bundle.get("data");
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        final LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.home_bar, null);

        txtTitle = view.findViewById(R.id.title);
        EditProfile = view.findViewById(R.id.edit);
        AddStadium = view.findViewById(R.id.add);

        toolbar.addView(view);
    }

    @Override
    public void setFragment(Fragment fragment, String Title , Bundle bundle) {
        if (!preFragmentName.equals(Title)) {
            fragment.setArguments(bundle);
            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack(Title)
                    .commitAllowingStateLoss();
            preFragmentName = Title;
            txtTitle.setText(Title);
        }else {
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Bundle bundle = new Bundle() ;
            bundle.putSerializable("data" , managerData );
            switch (menuItem.getItemId()){
                case R.id.reservation:
                    setFragment(new ReservationsFragment() , context.getString(R.string.reservations) , bundle);
                    break;
                case R.id.addStadium:
                    //setFragment(new AddStadiumFragment() , context.getString(R.string.add_stadium) , bundle);
                    break;
                case R.id.good:
                    //setFragment(new OrdersFragment() , getString(R.string.orders));
                    Toast.makeText(context, "good", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.profile:
                    setFragment(new ProfileFragment() ,context.getString(R.string.profile) , bundle);
                    break;
            }
            return true;
        }
    } ;

}
