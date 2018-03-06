package com.shun_minchang.interview_topics.main.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shun_minchang.interview_topics.main.model.DailyQuote;
import com.shun_minchang.interview_topics.main.presenter.IMainPresenter;
import com.shun_minchang.interview_topics.main.presenter.MainPresenter;
import com.shun_minchang.interview_topics.utils.Constants;
import com.shun_minchang.interview_topics.utils.Utils;

public class MainView extends AppCompatActivity implements IMainView {
    /**
     * UI Ids
     * CL = ConstraintLayout
     * TV = TextView
     * RV = RecyclerView
     * PB = ProgressBar
     */
    private static final int CL_ROOT_VIEW = 241;
    private static final int TV_DAILY_CONTENT = 572;
    private static final int TV_DAILY_SOURCE = 350;
    private static final int RV_WEATHER_LIST = 6;
    private static final int PB_PROGRESS = 565;

    private static final String TAG = "MainView";
    private IMainPresenter mMainPresenter;
    private BroadcastReceiver mBroadcastReceiver;
    private Handler mHandler;
    private IntentFilter mIntentFilter;
    private ConstraintLayout mCLRootView;
    private TextView mTVDailyContent, mTVDailySource;
    private RecyclerView mRVWeatherList;
    private ConstraintSet mConstraintSet;
    private ProgressBar mPBProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        // Init Root View
        mCLRootView = new ConstraintLayout(this);
        mCLRootView.setId(CL_ROOT_VIEW);
        mCLRootView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(mCLRootView);
        // Init Progress View
        initProgressView();
        // Init Daily Quote View
        initDailyQuoteView();
        // Init Weather List View
        initWeatherListView();
        // Init BroadcastReceiver
        initBroadcastReceiver();
        mHandler = new Handler();
    }

    private void initProgressView() {
        Log.d(TAG, "initProgressView: ");
        // Init Progress
        mPBProgress = new ProgressBar(this);
        mPBProgress.setId(PB_PROGRESS);
        mCLRootView.addView(mPBProgress);
        // ConstraintSet
        mConstraintSet = new ConstraintSet();
        mConstraintSet.constrainWidth(PB_PROGRESS, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.constrainHeight(PB_PROGRESS, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.connect(PB_PROGRESS, ConstraintSet.TOP,
                CL_ROOT_VIEW, ConstraintSet.TOP);
        mConstraintSet.connect(PB_PROGRESS, ConstraintSet.START,
                CL_ROOT_VIEW, ConstraintSet.START);
        mConstraintSet.connect(PB_PROGRESS, ConstraintSet.END,
                CL_ROOT_VIEW, ConstraintSet.END);
        mConstraintSet.connect(PB_PROGRESS, ConstraintSet.BOTTOM,
                CL_ROOT_VIEW, ConstraintSet.BOTTOM);
        mConstraintSet.applyTo(mCLRootView);
    }

    private void initDailyQuoteView() {
        Log.d(TAG, "initDailyQuoteView: ");
        // Init Daily Content
        int CONTENT_MARGIN = Utils.dpToPx(this, 8);
        int contentTextSize = 28;
        mTVDailyContent = new TextView(this);
        mTVDailyContent.setId(TV_DAILY_CONTENT);
        mTVDailyContent.setGravity(Gravity.LEFT);
        mTVDailyContent.setTextSize(contentTextSize);
        mCLRootView.addView(mTVDailyContent);
        // ConstraintSet
        mConstraintSet = new ConstraintSet();
        mConstraintSet.constrainWidth(TV_DAILY_CONTENT, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.constrainHeight(TV_DAILY_CONTENT, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.connect(TV_DAILY_CONTENT, ConstraintSet.TOP,
                CL_ROOT_VIEW, ConstraintSet.TOP, CONTENT_MARGIN);
        mConstraintSet.connect(TV_DAILY_CONTENT, ConstraintSet.START,
                CL_ROOT_VIEW, ConstraintSet.START, CONTENT_MARGIN);
        mConstraintSet.connect(TV_DAILY_CONTENT, ConstraintSet.END,
                CL_ROOT_VIEW, ConstraintSet.END, CONTENT_MARGIN);
        mConstraintSet.applyTo(mCLRootView);

        // Init Content Source
        int SOURCE_MARGIN = Utils.dpToPx(this, 8);
        int sourceTextSize = 16;
        mTVDailySource = new TextView(this);
        mTVDailySource.setId(TV_DAILY_SOURCE);
        mTVDailySource.setGravity(Gravity.RIGHT);
        mTVDailySource.setTextSize(sourceTextSize);
        mCLRootView.addView(mTVDailySource);
        // ConstraintSet
        mConstraintSet = new ConstraintSet();
        mConstraintSet.constrainWidth(TV_DAILY_SOURCE, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.constrainHeight(TV_DAILY_SOURCE, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.connect(TV_DAILY_SOURCE, ConstraintSet.TOP,
                TV_DAILY_CONTENT, ConstraintSet.BOTTOM, SOURCE_MARGIN);
        mConstraintSet.connect(TV_DAILY_SOURCE, ConstraintSet.START,
                CL_ROOT_VIEW, ConstraintSet.START, SOURCE_MARGIN);
        mConstraintSet.connect(TV_DAILY_SOURCE, ConstraintSet.END,
                CL_ROOT_VIEW, ConstraintSet.END, SOURCE_MARGIN);
        mConstraintSet.applyTo(mCLRootView);
    }

    private void initWeatherListView() {
        Log.d(TAG, "initWeatherListView: ");
        // Init Weather List
        int LIST_MARGIN = Utils.dpToPx(this, 8);
        mRVWeatherList = new RecyclerView(this);
        mRVWeatherList.setId(RV_WEATHER_LIST);
        mRVWeatherList.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        mCLRootView.addView(mRVWeatherList);
        // ConstraintSet
        mConstraintSet = new ConstraintSet();
        mConstraintSet.constrainWidth(RV_WEATHER_LIST, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.constrainHeight(RV_WEATHER_LIST, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.connect(RV_WEATHER_LIST, ConstraintSet.TOP,
                TV_DAILY_SOURCE, ConstraintSet.BOTTOM, LIST_MARGIN);
        mConstraintSet.connect(RV_WEATHER_LIST, ConstraintSet.START,
                CL_ROOT_VIEW, ConstraintSet.START, LIST_MARGIN);
        mConstraintSet.connect(RV_WEATHER_LIST, ConstraintSet.END,
                CL_ROOT_VIEW, ConstraintSet.END, LIST_MARGIN);
        mConstraintSet.connect(RV_WEATHER_LIST, ConstraintSet.BOTTOM,
                CL_ROOT_VIEW, ConstraintSet.BOTTOM, LIST_MARGIN);
        mConstraintSet.applyTo(mCLRootView);
    }

    private void initBroadcastReceiver() {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Constants.ACTION_GET_DAILY_QUOTE);
        mIntentFilter.addAction(Constants.ACTION_GET_WEATHER_OF_WEEK);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action == null || mHandler == null)
                    return;
                switch (action) {
                    case Constants.ACTION_GET_DAILY_QUOTE:
                        DailyQuote dailyQuote = intent.getParcelableExtra("DAILY_QUOTE");
                        mHandler.post(() -> updateDailyQuote(dailyQuote));
                        break;
                    case Constants.ACTION_GET_WEATHER_OF_WEEK:
                        mHandler.post(() -> updateWeatherList());
                        break;
                }
            }
        };
    }

    private void updateDailyQuote(DailyQuote dailyQuote) {
        // TODO: 2018/3/7 更新每日一句
        mTVDailyContent.setText(dailyQuote.getContent());
        mTVDailySource.setText(dailyQuote.getSource());
    }

    private void updateWeatherList() {
        // TODO: 2018/3/7 更新天氣資訊列表
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.registerNetworkBroadcastReceiver(this, mBroadcastReceiver, mIntentFilter);
        mMainPresenter.checkNetwork(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        mMainPresenter.unregisterNetworkBroadcastReceiver(this, mBroadcastReceiver);
        // TODO: 2018/3/7 釋放資源
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onNetworkChecked(boolean enabled) {
        Log.d(TAG, "onNetworkChecked: " + enabled);
        mPBProgress.setVisibility(View.GONE);
        if (!enabled) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("警告")
                    .setMessage("請檢查網路狀態!!")
                    .setPositiveButton("確認", (dialog, which) -> {
                        mMainPresenter.checkNetwork(this);
                        mPBProgress.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }).show();
        } else {
            mMainPresenter.getDailyQuote(this);
            mMainPresenter.getWeatherOfWeek();
        }
    }
}
