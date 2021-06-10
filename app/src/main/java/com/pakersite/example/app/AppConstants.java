package com.pakersite.example.app;

import android.os.Environment;

import java.io.File;

/**
 * 一些常量
 */
public class AppConstants {

    public static final int PLATFORM_AO_MEN = 2;
    public static final int PLATFORM_HK = 1;
    public static final String KEYBOARD = "KEYBOARD";
    public static final String VIEWGALLERY ="VIEWGALLERY" ;
    public static  int THIS_YEAR = 0;
    public static final String url_origin = "https://jc.49tkjc.com/unite49/app/index/apiHost";
    public static final String U_MENG_KEY = "5f1d3f68d62dd10bc71c0f24";
    public static final String J_PUSH_KEY = "0e2f2655419a951459f8061c";
    public static final String J_PUSH_SECRET = "875893eb8bdde375eaeb3fac";
    public static final String WX_APP_ID = "wxbc330b3ea5d96423";
    public static final String WX_APP_SECRET = "b3c70d7aee25805eda80abe5db3c466f";
    public static  boolean FROMPIC_AC = false;
    public static  int CHATROOMID = 0;
    public static final int DEFAULT_LOTTERY_PLATFORM = PLATFORM_AO_MEN;
    public static final String TOKEN = "token";
    public static final String USERNAME = "user_name";
    public static final String PHONE = "phone";
    public static final String USER_ID = "user_id";
    public static final String PASSWORD = "password";
    public static final String HUANXIN_TOKEN = "huanxin_token";
    public static final String HEADER_URL = "header_url";
    public static final String ISWXLOGIN = "is_wx_login";
    public static final String NVITECODE = "InviteCode";
    public static String shareType="";
    public static final String SHARECALLBACK = "SHARECALLBACK";
    public static final String WXLOGIN="wx_login";
    public static String regId="";

    public static  int screenwith=0;
    public static  int screenheight=0;
    public static  int itemSize=0;
    public static String PLATFORM_SELECTED = "platform_selected";
    public static final String PLATFORM_HK_NEXT_LOTTERY_TIME = "hk_next_lottery_time";
    public static final String PLATFORM_AO_MEN_NEXT_LOTTERY_TIME = "ao_men_next_lottery_time";
    public static final String PLATFORM_TAI_WAN_NEXT_LOTTERY_TIME = "tai_wan_next_lottery_time";
    public static final String PLATFORM_XIN_JIA_PO_NEXT_LOTTERY_TIME = "xin_jia_po_next_lottery_time";




    /**
     * APP 缓存文件夹根目录
     */
    public static final String APP_CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator+"EzChat";

    public static final String APP_CACHE_AUDIO = AppConstants.APP_CACHE_PATH + File.separator + "audio";

    public static final String APP_CACHE_IMAGE = AppConstants.APP_CACHE_PATH + File.separator + "image";

    public static final String APP_CACHE_VIDEO = AppConstants.APP_CACHE_PATH + File.separator + "video";





}
