//package nl.mranderson.pokefeeds;
//
//import android.support.test.espresso.contrib.RecyclerViewActions;
//import android.support.test.filters.MediumTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.LinkedList;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.mockito.Mockito.when;
//
///**
// * Instrumentation test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@MediumTest
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentationTest {
//    //TODO define my own list - stub it
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
//
//
//    @Test
//    public void useAppContext() throws Exception {
////        LinkedList mockedList = mock(LinkedList.class);
////
////
////        // stubbing appears before the actual execution
////        when(mockedList.get(0)).thenReturn("first");
//        // First scroll to the position that needs to be matched and click on it.
//        onView(withId(R.id.list))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//
//        // Match the text in an item below the fold and check that it's displayed.
//        onView(withText("yolo")).check(matches(isDisplayed()));
//    }
//
//
//    @Test
//    public void itemInMiddleOfList_hasSpecialText() {
//        // First, scroll to the view holder using the isInTheMiddle matcher.
//        onView(withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));
//
//        // Check that the item has the special text.
//        String middleElementText =
//                mActivityRule.getActivity().getResources().getString(R.string.middle);
//        onView(withText(middleElementText)).check(matches(isDisplayed()));
//    }
//}