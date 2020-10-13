package com.example.aalab6;

import android.Manifest;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    String carsList = "[{\"CAR_ID\":\"1\",\"OWNER\":\"TRAVIS\",\"AGE\":\"3\",\"BRAND\":\"SUZUKI\",\"NUMBER\":\"ZXY456GP\"},{\"CAR_ID\":\"2\",\"OWNER\":\"SCOTT\",\"AGE\":\"5\",\"BRAND\":\"SUBARU\",\"NUMBER\":\"XT54CCGP\"},{\"CAR_ID\":\"3\",\"OWNER\":\"ASTRID\",\"AGE\":\"5\",\"BRAND\":\"SMART\",\"NUMBER\":\"CTB492GP\"},{\"CAR_ID\":\"4\",\"OWNER\":\"WERLD\",\"AGE\":\"3\",\"BRAND\":\"SATURN\",\"NUMBER\":\"ZY54BTGP\"}]";
    @Test
    public void noInternetPermission(){
        ViewInteraction Display = onView(withId(R.id.textViewOut));
        Display.check(matches(withText("Before")));
    }

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET);
    @Test
    public void outputTest(){
        ViewInteraction Display = onView(withId(R.id.textViewOut));
        Display.check(matches(withText(carsList)));
    }
}