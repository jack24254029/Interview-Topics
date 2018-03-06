package com.shun_minchang.interview_topics.main.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        mTVDailyContent.setText("我是每日一句");
        mTVDailyContent.setGravity(Gravity.CENTER);
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
        mTVDailySource.setText("我是每日一句的來源");
        mTVDailySource.setGravity(Gravity.RIGHT | Gravity.CENTER);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
