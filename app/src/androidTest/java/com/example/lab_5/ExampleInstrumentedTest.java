package com.example.lab_5;

import android.content.res.Resources;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkTextEmpty () {
        onView(withId(R.id.txtMsg)).check(matches(withText(R.string.getText)));
    }

    @Test
    public void checkGetButton () throws InterruptedException {

        Thread.sleep(500);
        onView(withId(R.id.getButton)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.txtMsg)).check(matches(withText(R.string.getText)));
    }

    @Test
    public void checkPostButton () throws InterruptedException {

        onView(withId(R.id.postButton)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.txtMsg)).check(matches(withText(R.string.postText)));
    }

    @Test
    public void checkGetWrongButton () throws InterruptedException {

        Thread.sleep(500);
        onView(withId(R.id.getWrongButton)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.txtMsg)).check(matches(not(withText(R.string.getText))));
    }

    @Test
    public void checkPostWrongButton () throws InterruptedException {

        onView(withId(R.id.postWrongButton)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.txtMsg)).check(matches(not(withText(R.string.postText))));
    }

}
