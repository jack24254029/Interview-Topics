package com.shun_minchang.interview_topics.main.view;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by shun-minchang on 2018/3/7.
 */
@RunWith(AndroidJUnit4.class)
public class MainViewTest {
    @Rule
    public ActivityTestRule<MainView> mMainViewRule = new ActivityTestRule<>(MainView.class);

    @Test
    public void checkWeatherList() throws Exception {
        // 檢查1秒內，取得一週天氣，且要有14筆資料
        Thread.sleep(1000);
        mMainViewRule.getActivity();
        onView(withId(MainView.RV_WEATHER_LIST))
                .check(new RecyclerViewItemCountAssertion(14));
    }

    @Test
    public void checkDailyQuote() throws Exception {
        // 檢查3秒內，要取得每日一句
        Thread.sleep(3000);
        mMainViewRule.getActivity();
        onView(withId(MainView.TV_DAILY_CONTENT))
                .check(matches(not(withText(""))));
    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }
}