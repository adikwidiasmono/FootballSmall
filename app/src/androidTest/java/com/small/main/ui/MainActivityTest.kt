package com.small.main.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.small.main.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        // Check Bottom Navigation View
        onView(withId(bnv_football_match))
                .check(matches(isDisplayed()))
        // Check & Click Previous Match Button
        onView(withId(it_prev_match))
                .check(matches(isDisplayed()))
                .perform(click())
        // Check & Click Next Match Button
        onView(withId(it_next_match))
                .check(matches(isDisplayed()))
                .perform(click())
        // Check & Click Favorite Match Button
        onView(withId(it_favorites))
                .check(matches(isDisplayed()))
                .perform(click())

        // Back to Previous Match Button
        onView(withId(it_prev_match))
                .check(matches(isDisplayed()))
                .perform(click())

        // After open Previous Match Fragment, test recycle view
        onView(withId(rv_previous_match))
                .check(matches(isDisplayed()))
        onView(withId(rv_previous_match))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(rv_previous_match))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        // After click item from list, detail activity will be opened
        onView(withId(ll_match_detail))
                .check(matches(isDisplayed()))
        // Click favorite icon
        onView(withId(it_add_to_favorite))
                .check(matches(isDisplayed()))
                .perform(click())
        // Check is success add to favorite
        onView(withText("Added to favorite"))
                .check(matches(isDisplayed()));
        // Press back button to back to main menu
        onView(withId(ll_match_detail))
                .check(matches(isDisplayed()))
                .perform(pressBack())

        // Check & Click Favorite Match Button to check favorite list
        onView(withId(it_favorites))
                .check(matches(isDisplayed()))
                .perform(click())

        // After open Favorite Match Fragment, check recycle view
        onView(withId(rv_favorite_match))
                .check(matches(isDisplayed()))
        onView(withId(rv_favorite_match))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rv_favorite_match))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // After click item from list, detail activity will be opened
        onView(withId(ll_match_detail))
                .check(matches(isDisplayed()))
        // Click favorite icon
        onView(withId(it_add_to_favorite))
                .check(matches(isDisplayed()))
                .perform(click())
        // Check is success remove from favorite
        onView(withText("Removed from favorite"))
                .check(matches(isDisplayed()));

    }

}