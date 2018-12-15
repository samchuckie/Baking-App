package com.example.asce.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;
//import static org.hamcrest.Matchers.anything;
//import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipeTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private IdlingResourceEx idlingResourceEx;
    IdlingRegistry idlingRegistry;

    //    @Before
//    public void stubAllExternalIntents() {
//        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
//    }
    @Before
    public void registration(){
        idlingRegistry= IdlingRegistry.getInstance();
        idlingResourceEx = mActivityTestRule.getActivity().getIdlingresource();
        idlingRegistry.register(idlingResourceEx);
    }
    @Test
    public void networkTest(){
        // I did testing only on small phone oriented and not tablet
        // ondata does not work as recycleview is not an adpaterview anymore
        Espresso.onView(withId(R.id.allitems)).perform(click());
    }
    @After
    public void deregistration(){
        idlingRegistry.unregister(idlingResourceEx);
    }

}
