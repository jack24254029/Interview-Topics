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
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "AppDatabase";
    private static AppDatabase INSTANCE;

    public abstract WeatherDao weatherDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context,
                            AppDatabase.class,
                            AppDatabase.DB_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        if (INSTANCE != null)
            INSTANCE.close();
        INSTANCE = null;
    }
}
