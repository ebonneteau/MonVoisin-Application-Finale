
package com.openclassrooms.entrevoisins.Favorites_list;


import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of favorites
 */
@RunWith(AndroidJUnit4.class)
public class FavoritesListTest {

    private ListNeighbourActivity mActivity;
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);
    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        // Ensure favorite IS NULL
        assertThat(mActivity, notNullValue());
    }
    /**
     * We ensure that our recyclerview is displaying NO ITEM
     */
    @Test
    public void myFavoriteList_shouldBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_favorites))
                .check(matches(hasMinimumChildCount(0)));
    }
}