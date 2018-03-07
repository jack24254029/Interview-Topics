package com.shun_minchang.interview_topics.main.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import com.shun_minchang.interview_topics.database.entities.Weather;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public interface IMainPresenter {
    // 註冊網路監聽(用於網路請求完成或失敗)
    void registerNetworkBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    // 反註冊網路監聽
    void unregisterNetworkBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver);

    // 檢察網路狀態
    void checkNetwork(Context context);

    // 取得每日一句
    void getDailyQuote(Context context);

    // 取得一週天氣
    void getWeatherOfWeek(Context context);

    // 刪除一筆天氣資料
    void deleteWeatherData(Context context, Weather weather);

    // 釋放資源
    void release();
}
