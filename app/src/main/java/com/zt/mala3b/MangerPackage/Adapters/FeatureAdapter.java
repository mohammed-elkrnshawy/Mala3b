package com.zt.mala3b.MangerPackage.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zt.mala3b.MangerPackage.Models.FeatureResponses.Attribute;
import com.zt.mala3b.R;
import com.zt.mala3b.SharedPackage.ClassesPackage.CameraHelper;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.viewHolder> {

    private List<Attribute> data;
    private Context context;
    Map<String,Integer> featureMap;

    public FeatureAdapter(List<Attribute> data, Context context,Map<String,Integer> featureMap) {
        this.data = data;
        this.context = context;
        this.featureMap=featureMap;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_feature_select, parent, false);
        return new viewHolder(view);     }

    @Override
    public void onBindViewHolder(FeatureAdapter.viewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getImage()).placeholder(context.getDrawable(R.drawable.ic_feature))
                .into(holder.Image);

        holder.txtName.setText(data.get(position).getName());

        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    featureMap.put(data.get(position).getName(),data.get(position).getId());
                }
                else {
                    featureMap.remove(data.get(position).getName(),data.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        public ImageView Image;
        public TextView txtName;
        public CheckBox radioButton;

        public viewHolder(View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.imgImage);
            txtName=itemView.findViewById(R.id.txtName);
            radioButton=itemView.findViewById(R.id.check);
        }

    }



}
