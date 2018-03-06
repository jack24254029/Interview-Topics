package com.shun_minchang.interview_topics.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class DailyQuote implements Parcelable {
    private static final String TAG = "DailyQuote";
    private String content;
    private String source;

    public DailyQuote(String content, String source) {
        this.content = content;
        this.source = source;
    }

    protected DailyQuote(Parcel in) {
        content = in.readString();
        source = in.readString();
    }

    public static final Creator<DailyQuote> CREATOR = new Creator<DailyQuote>() {
        @Override
        public DailyQuote createFromParcel(Parcel in) {
            return new DailyQuote(in);
        }

        @Override
        public DailyQuote[] newArray(int size) {
            return new DailyQuote[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(source);
    }
}
