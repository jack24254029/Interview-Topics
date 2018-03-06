package com.shun_minchang.interview_topics.main.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shun_minchang.interview_topics.main.model.DailyQuote;
import com.shun_minchang.interview_topics.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
                .url("https://tw.appledaily.com/index/dailyquote/")
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
                Log.d(TAG, "onResponse: " + content);
                Log.d(TAG, "onResponse: " + source);
                DailyQuote dailyQuote = new DailyQuote(content, source);
                Intent intent = new Intent(Constants.ACTION_GET_DAILY_QUOTE);
                intent.putExtra("DAILY_QUOTE", dailyQuote);
                context.sendBroadcast(intent);
            }
        });
    }

}
