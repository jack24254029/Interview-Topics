package com.shun_minchang.interview_topics.main.presenter;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public interface IMainPresenter {
    // 註冊網路監聽(用於網路請求完成或失敗)
    void registerNetworkBroadcastReceiver();

    // 反註冊網路監聽
    void unregisterNetworkBroadcastReceiver();

    // 檢察網路狀態
    void checkNetwork();

    // 取得每日一句
    void getDailyQuote();

    // 取得一週天氣
    void getWeatherOfWeek();
}
