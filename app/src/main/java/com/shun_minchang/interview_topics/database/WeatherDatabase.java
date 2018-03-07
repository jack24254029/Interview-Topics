package com.shun_minchang.interview_topics.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shun_minchang.interview_topics.database.dao.WeatherDao;
import com.shun_minchang.interview_topics.database.entities.Weather;

/**
 * Created by shun-minchang on 2018/3/7.
 */
@Database(entities = {Weather.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public static final String DB_NAME = "Weather";
    private static WeatherDatabase INSTANCE;

    public abstract WeatherDao weatherDao();

    public static WeatherDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context,
                            WeatherDatabase.class,
                            WeatherDatabase.DB_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public static void destoryInstance() {
        if (INSTANCE != null)
            INSTANCE.close();
        INSTANCE = null;
    }
}
