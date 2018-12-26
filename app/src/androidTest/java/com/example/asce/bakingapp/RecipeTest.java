package com.example.asce.bakingapp;


import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.asce.bakingapp.Constant.Const.MAIN_ACTIVITY;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class RecipeTest {
    private IdlingResourceEx idlingResourceEx;
    private IdlingRegistry idlingRegistry;
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void registration(){
        idlingRegistry= IdlingRegistry.getInstance();
        idlingResourceEx = intentsTestRule.getActivity().getIdlingresource();
        intentsTestRule.getActivity().callNetwork();
        idlingRegistry.register(idlingResourceEx);
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }
    @Test
    public void intentTest(){
        // I did testing only on small phone oriented and not tablet
        // ondata does not work as recycleview is not an adpaterview anymore
        onView(withId(R.id.all_items)).perform(click());
        // Check that the intent has an extra with the key.
        intended(hasExtraWithKey(MAIN_ACTIVITY));
    }
    @Test
    public void ingredientTest(){
        onView(withId(R.id.all_items)).perform(click());
        onView(withId(R.id.ing)).check(matches(withText(R.string.ingredients)));
        onView(withId(R.id.ing)).perform(click());
    }
    @After
    public void deregistration(){
        idlingRegistry.unregister(idlingResourceEx);
    }

}
