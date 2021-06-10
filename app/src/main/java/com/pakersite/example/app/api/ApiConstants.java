package com.pakersite.example.app.api;


import android.text.TextUtils;

import com.pakersite.example.app.AppConstants;
import com.pakersite.example.app.utils.SPUtils;

/**
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 */
public class ApiConstants {
    //http://47.112.32.221:8100/unite49/unite49-android

    //正式 https://api.www49actk.com/  测试 ： http://39.108.190.82/
//    public static String APP_DOMAIN = "http://18.167.85.209:5500";
//    public static String APP_DOMAIN = "http://18.167.85.209:5500/";

    public static  final String APP_DOMAIN = "https://api.49tkcc.com/";

    public static final int RequestSuccess = 10000;
    // ws://ws.www49actk.com   ws://chat.49ttkk.com/app/websocket/
//    public static String SOCKET_URL = "ws://18.167.85.209:5500";
    public static final String SOCKET_URL = "ws://ws.www49actk.com/";
    public static final String Token = "authorization";
//    String SOCKET_URL = "ws://www.49other.com/app/websocket/" + SPUtils.getInstance().getString(AppConstants.TOKEN);
}
