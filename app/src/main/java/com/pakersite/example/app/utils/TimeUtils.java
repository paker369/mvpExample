package com.pakersite.example.app.utils;



import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getTime_yyyy(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getTimeText(Date date, String pattern) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date date, String formatType) {
        return new SimpleDateFormat(formatType).format(date);
    }


    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 舍弃掉是分秒
     */
    public static String getTimeYmd(String createTime) {
        String time = "";
        if (!TextUtils.isEmpty(createTime)) {
            time = createTime.split(" ")[0];
        }
        return time;
    }
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static String longToDate(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
//        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return sDateTime;
    }
}
