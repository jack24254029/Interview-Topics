package com.shun_minchang.interview_topics.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;
import com.shun_minchang.interview_topics.R;
import com.shun_minchang.interview_topics.database.entities.Weather;
import com.shun_minchang.interview_topics.database.service.DBJobService;
import com.shun_minchang.interview_topics.main.model.DailyQuote;
import com.shun_minchang.interview_topics.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shun-minchang on 2018/3/7.
 */

public class NetWorkController {
    private static final String TAG = "NetWorkController";
    private OkHttpClient mClient;

    private static class NetWorkControllerHolder {
        private static final NetWorkController INSTANCE = new NetWorkController();
    }

    private NetWorkController() {
        mClient = new OkHttpClient();
    }

    public static NetWorkController getInstance() {
        return NetWorkControllerHolder.INSTANCE;
    }

    public void getDailyQuote(Context context) {
        Request request = new Request.Builder()
                .url(Constants.DAILY_QUOTE_URL)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Document document = Jsoup.parse(response.body().string());
                String content = document.select("article[class=dphs] > p").text();
                String source = document.select("article[class=dphs] > h1").text();
                DailyQuote dailyQuote = new DailyQuote(content, source);
                Intent intent = new Intent(Constants.ACTION_GET_DAILY_QUOTE);
                intent.putExtra(context.getString(R.string.key_daily_quote), dailyQuote);
                context.sendBroadcast(intent);
            }
        });
    }

    public void getWeatherOfWeek(Context context) {
        Parser parser = new Parser();
        parser.execute(Constants.WEATHER_OF_WEEK_URL);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                String weatherDescription = list.get(1).getDescription();
                String[] weatherDatas = weatherDescription
                        .replace("溫度:", "")
                        .split("<BR>");
                for (String data : weatherDatas) {
                    String[] weatherData = data.split(" ");
                    if (weatherData.length == 6) {
                        // 去除多餘空白
                        String date = weatherData[0].replaceAll("\\s+", "");
                        String dayOrNight = weatherData[1];
                        String temperature = weatherData[2] + weatherData[3] + weatherData[4];
                        String description = weatherData[5];
                        Weather weather = new Weather(date, dayOrNight, temperature, description);
                        // 新增資料
                        Intent intent = new Intent(Constants.ACTION_INSERT_DATA);
                        intent.putExtra(context.getString(R.string.key_weather_data), weather);
                        DBJobService.enqueueWork(context, intent);
                    }
                }
                // 新增完畢後，取得全部資料傳給View
                Intent intent = new Intent(Constants.ACTION_GET_ALL_DATA);
                DBJobService.enqueueWork(context, intent);
            }

            @Override
            public void onError() {

            }
        });
    }

}
