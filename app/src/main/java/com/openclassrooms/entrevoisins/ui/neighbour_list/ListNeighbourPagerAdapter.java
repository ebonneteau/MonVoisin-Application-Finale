package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ListNeighbourPagerAdap";

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * get the number of pages
     *
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
    /**
     * getItem is called to instantiate the fragment for the given page.
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {//TODO add new fragment, use switch/Case or if like in myapplication (fragments/PageAdapter)
        if (position == 0) {
            Log.d(TAG, "getItem() called with: position = [" + position + "]"); //TODO ask why I don't see the log !
            return NeighbourFragment.newInstance(0);
        }
        if (position == 1) {
            Log.d(TAG, "getItem() called with: position = [" + position + "]");
            return FavoriteFragment.newInstance(1);
        } else
            return null;
    }





}