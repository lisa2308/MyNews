package com.example.lisap.mynews;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.lisap.mynews.activities.SearchSettingsActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchSettingsActivity> mActivityTestRule =
            new ActivityTestRule<>(SearchSettingsActivity.class, false, false);
    @Test
    public void checkNotificationLayoutNoVisible(){
        Intent i = new Intent();
        i.putExtra("isSearch", true);
        mActivityTestRule.launchActivity(i);

        onView(withId(R.id.activity_search_settings_linear_notif)).check(matches(not(isDisplayed())));

    }

    @Test
    public void checkSearchLayoutNoVisible(){
        Intent i = new Intent();
        i.putExtra("isSearch", false);
        mActivityTestRule.launchActivity(i);

        onView(withId(R.id.activity_search_settings_button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.activity_search_settings_linear_date)).check(matches(not(isDisplayed())));

    }

    @Test
    public void clickOnSearchButton(){
        Intent i = new Intent();
        i.putExtra("isSearch", true);
        mActivityTestRule.launchActivity(i);

        onView(withId(R.id.activity_search_settings_button)).perform(click());

        onView(withText("Please enter a query"))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
}
