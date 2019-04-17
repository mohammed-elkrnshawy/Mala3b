package com.zt.mala3b.SharedPackage.ClassesPackage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.zt.mala3b.R;

import static android.content.Context.MODE_PRIVATE;

public class SharedClass {

    public static boolean isLogin=false;
    private static Dialog progressDialog;

    public static String getLocalization(Context context)
    {
        return context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).getString("language","ar") ;
    }

    public static void ShowWaiting(Context context) {
        progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.view_waiting);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideWaiting() {
        progressDialog.dismiss();
    }

}
