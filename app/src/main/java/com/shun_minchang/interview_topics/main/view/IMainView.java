package com.shun_minchang.interview_topics.main.view;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public interface IMainView {
    // 當網路檢查完成
    void onNetworkChecked(boolean enabled);

    // 當取得每日一句
    void onGetDailyQuote(String content, String source);

    // 當取得一週天氣預報
    void onGetWeatherOfWeek();
}
