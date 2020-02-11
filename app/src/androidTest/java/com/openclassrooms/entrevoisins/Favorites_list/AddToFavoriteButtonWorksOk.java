package com.openclassrooms.entrevoisins.Favorites_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddToFavoriteButtonWorksOk {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void addToFavoriteButtonWorksOk() {
        //Given we locate at neighbour Tab view
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.parent_layout),
                        childAtPosition(
                                allOf(withId(R.id.list_neighbours),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_name), withText("Chloé"),
                        childAtPosition(
                                allOf(withId(R.id.parent_layout),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                2)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Chloé")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(
                                allOf(withId(R.id.parent_layout),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                2)),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(
                                allOf(withId(R.id.parent_layout),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                2)),
                                0),
                        isDisplayed()));
        //When clicking on child at position 2 - Avatar named Chloé
        appCompatImageView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Then NeighbourDetails Activity is launched and showing details of Chloé avatar.

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.neighbor_detail_name), withText("Chloé"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Chloé")));
        // Given Button to add to favorites exists


        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_to_favorite_bt),

                        isDisplayed()));
        //When clicking on this button
        floatingActionButton.perform(click());
        //When clicking on back button
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.toolbar_layout), withContentDescription("Chloé")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //When back to Favorite tab view
        //Then we ensure a favorite exists in favorite tab at position 0
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction viewGroup2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.list_favorites),
                                withParent(withId(R.id.container))),
                        0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));
        //Then we ensure also this child view is Chloé

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_list_name), withText("Chloé"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_favorites),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("Chloé")));

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_favorites),
                                        0),
                                0),
                        isDisplayed()));
        // Given we turn back into Chloé details
        appCompatImageView2.perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.add_to_favorite_bt),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        //When we click again on favorite button
        floatingActionButton2.perform(click());
        //And When we go back to favorite recycler view
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.toolbar_layout), withContentDescription("Chloé")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_favorites),
                        withParent(allOf(withId(R.id.container),
                                childAtPosition(
                                        withId(R.id.main_content),
                                        1))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
        //Espresso code modified manually

        //Then Container favorite list item is empty
        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(matches(hasMinimumChildCount(0)));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
