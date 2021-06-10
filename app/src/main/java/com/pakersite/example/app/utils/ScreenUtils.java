package com.pakersite.example.app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }

    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        return height;
    }
}
