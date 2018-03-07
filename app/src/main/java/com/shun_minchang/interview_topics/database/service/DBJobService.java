package com.shun_minchang.interview_topics.database.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import com.shun_minchang.interview_topics.R;
import com.shun_minchang.interview_topics.database.AppDatabase;
import com.shun_minchang.interview_topics.database.dao.WeatherDao;
import com.shun_minchang.interview_topics.database.entities.Weather;
import com.shun_minchang.interview_topics.utils.Constants;

import java.util.ArrayList;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class DBJobService extends JobIntentService {
    private static final String TAG = "DBJobService";
    static final int JOB_ID = 999999;
    private WeatherDao weatherDao;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, DBJobService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        weatherDao = AppDatabase.getDatabase(this).weatherDao();
    }

    @Override
    protected void onHandleWork(Intent intent) {
        long id;
        String action = intent.getAction();
        if (action == null)
            return;
        Weather weather = intent.getParcelableExtra(getString(R.string.key_weather_data));
        switch (action) {
            case Constants.ACTION_GET_ALL_DATA:
                intent = new Intent(Constants.ACTION_GET_WEATHER_OF_WEEK);
                intent.putParcelableArrayListExtra(getString(R.string.key_weather_of_week),
                        (ArrayList<Weather>) weatherDao.getAll());
                sendBroadcast(intent);
                Log.d(TAG, "onHandleWork: Get All Data");
                break;
            case Constants.ACTION_INSERT_DATA:
                id = weatherDao.insert(weather);
                Log.d(TAG, "onHandleWork: Insert Data, Uid:" + id);
                break;
            case Constants.ACTION_DELETE_DATA:
                weatherDao.delete(weather);
                Log.d(TAG, "onHandleWork: Delete A Data");
                break;
            case Constants.ACTION_DELETE_ALL_DATA:
                weatherDao.deleteAll();
                Log.d(TAG, "onHandleWork: Delete All Data");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        weatherDao = null;
        AppDatabase.destroyInstance();
    }
}
