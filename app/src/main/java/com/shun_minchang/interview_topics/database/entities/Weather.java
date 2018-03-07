package com.shun_minchang.interview_topics.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static com.shun_minchang.interview_topics.database.entities.Weather.TABLE_WEATHER;

/**
 * Created by shun-minchang on 2018/3/7.
 */
@Entity(tableName = TABLE_WEATHER)
public class Weather implements Parcelable {
    public static final String TABLE_WEATHER = "weather";
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String date;
    private String dayOrNight;
    private String temperature;
    private String description;

    public Weather(String date, String dayOrNight, String temperature, String description) {
        this.date = date;
        this.dayOrNight = dayOrNight;
        this.temperature = temperature;
        this.description = description;
    }

    protected Weather(Parcel in) {
        uid = in.readInt();
        date = in.readString();
        dayOrNight = in.readString();
        temperature = in.readString();
        description = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOrNight() {
        return dayOrNight;
    }

    public void setDayOrNight(String dayOrNight) {
        this.dayOrNight = dayOrNight;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getDate() + "," + getDayOrNight() + "," + getTemperature() + "," + getDescription();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(date);
        dest.writeString(dayOrNight);
        dest.writeString(temperature);
        dest.writeString(description);
    }
}
