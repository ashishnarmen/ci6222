package com.ci6222.dictionary;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UITests {

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
        waitToFetchWord();
        onView(withId(R.id.word)).check(matches(withText(defaultWord)));
    }

    @Test
    public void enterWord() {
        String news= "news";
        onView(withId(R.id.etWord))
                .perform(clearText())
                .perform(typeText(news), closeSoftKeyboard());
        onView(withId(R.id.etWord)).check(matches(withText(news)));
    }

    @Test
    public void searchWord() {
        String hello= "hello";
        String origin = "early 19th century: variant of earlier hollo ; related to holla.";
        onView(withId(R.id.etWord))
                .perform(clearText())
                .perform(typeText(hello), closeSoftKeyboard());
        onView((withId(R.id.btn_search_word))).perform(click());
        waitToFetchWord();
        onView(withId(R.id.word)).check(matches(withText(hello)));
        onView(withId(R.id.origin)).check(matches((withText(origin))));
    }

    private void waitToFetchWord() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void navigation() {
        String defaultWord = "the";
        String hello= "hello";
        onView(withId(R.id.etWord))
                .perform(clearText())
                .perform(typeText(hello), closeSoftKeyboard());
        onView((withId(R.id.btn_search_word))).perform(click());
        waitToFetchWord();
        onView(withId(R.id.word)).check(matches(withText(hello)));
        pressBack();
        waitToFetchWord();
        onView(withId(R.id.word)).check(matches(withText(defaultWord)));
    }

}