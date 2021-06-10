package com.pakersite.example.app.utils.network;

public interface NetStateChangeObserver {
    void onNetDisconnected();
    void onNetConnected(NetworkType networkType);

}