package com.zt.mala3b.MangerPackage.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.mala3b.R;
import com.zt.mala3b.SharedPackage.ClassesPackage.CameraHelper;

import java.util.List;

public class PhotoAddAdapter extends RecyclerView.Adapter<PhotoAddAdapter.viewHolder> {

    private List<Bitmap> data;
    private Context context;
    private CameraHelper cameraHelper;

    public PhotoAddAdapter(List<Bitmap> data, Context context, CameraHelper cameraHelper) {
        this.data = data;
        this.context = context;
        this.cameraHelper=cameraHelper;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_photo_add, parent, false);
        return new viewHolder(view);     }

    @Override
    public void onBindViewHolder(PhotoAddAdapter.viewHolder holder, final int position) {
        holder.Image.setImageBitmap(data.get(position));

        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    cameraHelper.SelectPhotoDialog();
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

        public viewHolder(View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.photo);
        }

    }



}
