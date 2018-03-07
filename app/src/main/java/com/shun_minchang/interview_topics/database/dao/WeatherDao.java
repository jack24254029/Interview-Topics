package com.shun_minchang.interview_topics.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shun_minchang.interview_topics.database.entities.Weather;

import java.util.List;

/**
 * Created by shun-minchang on 2018/3/7.
 */
@Dao
public interface WeatherDao {
    @Query("select * from " + Weather.TABLE_WEATHER)
    public List<Weather> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Weather weather);

    @Delete
    public void delete(Weather weather);

    @Query("DELETE FROM " + Weather.TABLE_WEATHER)
    public void deleteAll();
}
