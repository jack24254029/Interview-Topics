package com.shun_minchang.interview_topics.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by shun-minchang on 2018/3/6.
 */

public class Utils {
    private static final String TAG = "Utils";

    public static int dpToPx(Context context, int dp) {
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics());
        return (int) px;
    }
}
