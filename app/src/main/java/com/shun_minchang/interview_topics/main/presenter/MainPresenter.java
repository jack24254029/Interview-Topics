package com.shun_minchang.interview_topics.main.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.shun_minchang.interview_topics.main.network.NetWorkController;
import com.shun_minchang.interview_topics.main.view.IMainView;

import java.net.InetAddress;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class MainPresenter implements IMainPresenter {
    private static final String TAG = "MainPresenter";
    private IMainView mainView;
    private Handler mHandler;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        mHandler = new Handler();
    }

    @Override
    public void registerNetworkBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void unregisterNetworkBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void checkNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            checkNetWorkAvailable();
        } else {
            onNetworkEnabled(false);
        }
    }

    private void checkNetWorkAvailable() {
        new Thread(() -> {
            boolean isEnabled = false;
            try {
                InetAddress inetAddress = InetAddress.getByName("www.google.com");
                isEnabled = !inetAddress.getHostAddress().isEmpty();
            } catch (Exception e) {
                Log.d(TAG, "checkNetWorkAvailable: " + e.getLocalizedMessage());
            } finally {
                final boolean result = isEnabled;
                mHandler.post(() -> onNetworkEnabled(result));
            }
        }).start();
    }

    private void onNetworkEnabled(boolean enabled) {
        Log.d(TAG, "onNetworkEnabled: " + enabled);
        mainView.onNetworkChecked(enabled);
    }

    @Override
    public void getDailyQuote(Context context) {
        // TODO: 2018/3/7 發送每日一句的網路請求
        NetWorkController.getInstance().getDailyQuote(context);
    }

    @Override
    public void getWeatherOfWeek() {
        // TODO: 2018/3/7 發送台中市一週的天氣預報網路請求
    }
}
