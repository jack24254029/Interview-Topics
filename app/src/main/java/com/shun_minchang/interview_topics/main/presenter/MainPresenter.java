package com.shun_minchang.interview_topics.main.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.shun_minchang.interview_topics.R;
import com.shun_minchang.interview_topics.database.entities.Weather;
import com.shun_minchang.interview_topics.database.service.DBJobService;
import com.shun_minchang.interview_topics.main.view.IMainView;
import com.shun_minchang.interview_topics.network.NetWorkController;
import com.shun_minchang.interview_topics.utils.Constants;

import java.net.InetAddress;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class MainPresenter implements IMainPresenter {
    private static final String TAG = "MainPresenter";
    private IMainView mMainView;
    private Handler mHandler;

    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
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
        if (mMainView == null)
            return;
        Log.d(TAG, "onNetworkEnabled: " + enabled);
        mMainView.onNetworkChecked(enabled);
    }

    @Override
    public void getDailyQuote(Context context) {
        NetWorkController.getInstance().getDailyQuote(context);
    }

    @Override
    public void getWeatherOfWeek(Context context) {
        // 先刪除全部資料
        Intent intent = new Intent(Constants.ACTION_DELETE_ALL_DATA);
        DBJobService.enqueueWork(context, intent);

        NetWorkController.getInstance().getWeatherOfWeek(context);
    }

    @Override
    public void deleteWeatherData(Context context, Weather weather) {
        Intent intent = new Intent(Constants.ACTION_DELETE_DATA);
        intent.putExtra(context.getString(R.string.key_weather_data), weather);
        DBJobService.enqueueWork(context, intent);
    }

    @Override
    public void release() {
        mHandler = null;
        mMainView = null;
    }
}
