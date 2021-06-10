package com.pakersite.example.app.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by Carson_Ho on 16/10/31.
 */
public class NetWorkState {
    public static boolean isWifiState(Context context){
        boolean isState=false;
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {

//                isState=true;
//            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {

//                isState=true;
//            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {

//                isState=false;
//            } else {
//                isState=false;
//            }
            isState=wifiNetworkInfo.isConnected();
        }else {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
                if(networkInfo.getTypeName().equals("WIFI")){
                    isState=true;
                }else{
                    isState=false;
                }
            }
        }
        return isState;
    }

}
