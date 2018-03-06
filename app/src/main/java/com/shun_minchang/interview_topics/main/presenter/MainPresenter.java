package com.shun_minchang.interview_topics.main.presenter;

import com.shun_minchang.interview_topics.main.view.IMainView;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class MainPresenter implements IMainPresenter {
    private static final String TAG = "MainPresenter";
    private IMainView mainView;

    MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void registerNetworkBroadcastReceiver() {

    }

    @Override
    public void unregisterNetworkBroadcastReceiver() {

    }

    @Override
    public void checkNetwork() {

    }

    @Override
    public void getDailyQuote() {

    }

    @Override
    public void getWeatherOfWeek() {

    }
}
