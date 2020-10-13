package com.example.lab7;

import android.Manifest;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
    public void noInternetPermission(){
        ViewInteraction Display = onView(withId(R.id.textViewOut));
        Display.check(matches(withText("Before")));
    }

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET);
    @Test
    public void outputTest() throws InterruptedException {
        Thread.sleep(1000);
        ViewInteraction Display = onView(withId(R.id.textViewOut));
        Display.check(matches(withText(R.string.carsList)));
    }

    @Test
    public void car_1() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.car_1)).check(matches(withText(R.string.car_1)));

    }

    @Test
    public void car_2() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.car_2)).check(matches(withText(R.string.car_2)));
    }

    @Test
    public void car_3() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.car_3)).check(matches(withText(R.string.car_3)));

    }

    @Test
    public void car_4() throws InterruptedException {
        Thread.sleep(1000);

        onView(withId(R.id.car_4)).check(matches(withText(R.string.car_4)));
    }
}
