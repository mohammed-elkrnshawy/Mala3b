package com.zt.mala3b.SharedPackage.ClassesPackage;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class SharedClass {

    public static String getLocalization(final Context context)
    {
        String loc= context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE).getString("language","ar") ;
        return loc;
    }
}
