package com.ci6222.dictionary;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.ci6222.dictionary", appContext.getPackageName());
    }

    @Test
    public void checkDefaultWord() {
        String defaultWord = "the";
        onView(withId(R.id.word)).check(matches(withText(defaultWord)));
    }

    @Test
    public void enterWord() {
        String hello= "hello";
        onView(withId(R.id.word))
                .perform(clearText())
                .perform(typeText(hello), closeSoftKeyboard());
        onView(withId(R.id.word)).check(matches(withText(hello)));
        onView((withId(R.id.btn_search_word))).perform(click());
        // early 19th century: variant of earlier hollo ; related to holla.
        // həˈləʊ
        // used as a greeting or to begin a phone conversation.
    }
}