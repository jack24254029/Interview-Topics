package com.shun_minchang.interview_topics;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.shun_minchang.interview_topics.database.WeatherDatabase;
import com.shun_minchang.interview_topics.database.dao.WeatherDao;
import com.shun_minchang.interview_topics.database.entities.Weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

/**
 * Created by shun-minchang on 2018/3/7.
 */
@RunWith(AndroidJUnit4.class)
public class EntityTest {
    private WeatherDao weatherDao;
    private WeatherDatabase weatherDatabase;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        weatherDatabase = Room.inMemoryDatabaseBuilder(context, WeatherDatabase.class).build();
        weatherDao = weatherDatabase.weatherDao();
    }

    @After
    public void closeDb() throws IOException {
        weatherDatabase.close();
    }

    @Test
    public void writeAndReadInList() throws Exception {
        // 檢查新增的資料是否新增成功
        Weather weather = new Weather("09-06", "白天", "溫度:22~30", "超級大太陽");
        long id = weatherDao.insert(weather);
        List<Weather> weathers = weatherDao.getAll();
        assert weathers.get(weathers.size() - 1).getUid() == id;
    }

    @Test
    public void readInList() throws Exception {
        // 檢查取資料是否成功
        List<Weather> weathers = weatherDao.getAll();
        assert weathers.size() > 0;
    }

    @Test
    public void deleteAll() throws Exception {
        // 檢查刪除全部資料是否成功
        weatherDao.deleteAll();
        List<Weather> weathers = weatherDao.getAll();
        assert weathers.size() == 0;
    }
}
