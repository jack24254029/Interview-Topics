package com.shun_minchang.interview_topics.main.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shun_minchang.interview_topics.R;
import com.shun_minchang.interview_topics.database.entities.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.WeatherViewHolder> {
    private static final String TAG = "WeatherRVAdapter";
    private List<Weather> weatherList;
    private OnLongClickListener mOnLongClickListener;

    public interface OnLongClickListener {
        void onLongClick(Weather weather);
    }

    public WeatherRVAdapter() {
        weatherList = new ArrayList<>();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        // 設定背景有點擊的效果
        TypedArray a = parent.getContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.selectableItemBackground});
        linearLayout.setBackground(a.getDrawable(0));
        a.recycle();
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new WeatherViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.mTVDate.setText(weather.getDate());
        holder.mTVDayOrNight.setText(weather.getDayOrNight());
        holder.mTVTemperature.setText("溫度：" + weather.getTemperature());
        holder.mTVDescription.setText(weather.getDescription());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    public void delete(Weather weather) {
        int index = weatherList.indexOf(weather);
        if (index < 0)
            return;
        weatherList.remove(index);
        notifyItemRemoved(index);
    }

    public void deleteAll() {
        if (weatherList.size() == 0)
            return;
        notifyItemRangeRemoved(0, weatherList.size());
        weatherList.clear();
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView mTVDate, mTVDayOrNight, mTVTemperature, mTVDescription;

        WeatherViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            LinearLayout linearLayout = (LinearLayout) itemView;

            mTVDate = new TextView(context);
            TextViewCompat.setTextAppearance(mTVDate, R.style.TextAppearance_AppCompat_Medium);
            mTVDate.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(mTVDate);

            mTVDayOrNight = new TextView(context);
            TextViewCompat.setTextAppearance(mTVDayOrNight, R.style.TextAppearance_AppCompat_Medium);
            mTVDayOrNight.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(mTVDayOrNight);

            mTVTemperature = new TextView(context);
            TextViewCompat.setTextAppearance(mTVTemperature, R.style.TextAppearance_AppCompat_Medium);
            mTVTemperature.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(mTVTemperature);

            mTVDescription = new TextView(context);
            TextViewCompat.setTextAppearance(mTVDescription, R.style.TextAppearance_AppCompat_Medium);
            mTVDescription.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(mTVDescription);

            // Event
            itemView.setOnLongClickListener(v -> {
                if (mOnLongClickListener != null) {
                    mOnLongClickListener.onLongClick(weatherList.get(getAdapterPosition()));
                }
                return true;
            });
        }
    }
}
