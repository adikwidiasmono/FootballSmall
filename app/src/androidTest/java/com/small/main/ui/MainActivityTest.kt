package com.small.main.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.small.main.R.id.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

//    @Before
//    fun init() {
//        activityRule.getActivity()
//                .getSupportFragmentManager().beginTransaction();
//    }

    @Test
    fun testRecyclerViewBehaviour() {
        // Check Bottom Navigation View
        onView(withId(bnv_football_match))
                .check(matches(isDisplayed()))
        // Click Previous Match Button
        onView(withId(it_prev_match))
                .check(matches(isDisplayed()))
        onView(withId(it_prev_match))
                .perform(click())
        // Click Next Match Button
        onView(withId(it_next_match))
                .check(matches(isDisplayed()))
        onView(withId(it_next_match))
                .perform(click())
        // Click Favorite Match Button
        onView(withId(it_favorites))
                .check(matches(isDisplayed()))
        onView(withId(it_favorites))
                .perform(click())


//        // After open Previous Match Fragment, test recycle view
//        onView(withId(list_team))
//                .check(matches(isDisplayed()))
//        onView(withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
//        onView(withId(list_team)).perform(
//                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

}