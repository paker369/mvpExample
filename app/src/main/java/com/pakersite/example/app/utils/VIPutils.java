package com.pakersite.example.app.utils;

import android.graphics.Color;
import android.widget.TextView;

/**
 * @author YangJ
 * @date 2021/2/1 10:13.
 * description
 */
public class VIPutils {
    /**
     * 设置VIP昵称颜色
     * @param vip   1:是vip 2:不是vip
     * @param textView  tv
     */
    public static void setVipNameColor(int vip, TextView textView){
        if (vip == 1) {
            textView.setTextColor(Color.parseColor("#FF4545"));
        }else{
            textView.setTextColor(Color.parseColor("#333333"));
        }
    }

    /**
     * 获取vip昵称颜色
     */
    public static int getVipColor(int vip){
        if (vip == 1) {
            return Color.parseColor("#FF4545");
        }
        return Color.parseColor("#333333");
    }
}
