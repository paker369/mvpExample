package com.pakersite.example.app.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * @author Created by YangJ
 * @date 1/30/21 7:31 PM
 * <p>
 * description:
 */
public class NumberUtils {

    /**
     * 数量转rmb 展示
     */
    public static String getRMB(Object num){
        String money;
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            money = df.format(num);
        }catch (Exception e){
            e.printStackTrace();
            money = String.valueOf(num);
        }
        return money;
    }

    /**
     * 获取期数 001
     */
    public static String get000(Object num){
        String money;
        try {
            DecimalFormat df = new DecimalFormat("000");
            money = df.format(num);
        }catch (Exception e){
            e.printStackTrace();
            money = String.valueOf(num);
        }
        return money;
    }

    /**
     * 点击数+1
     */
    public static String setClickNum(String num){
        if (TextUtils.isEmpty(num)) {
            return "1";
        }
        try {
            int count = Integer.parseInt(num);
            return String.valueOf(count + 1);
        } catch (Exception e){
            e.printStackTrace();
            return num;
        }

    }
}
