package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;


public class FavoriteFragment extends Fragment {
    private NeighbourApiService mApiService;
    public List<Neighbour> mFavorite;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     *
     * @param i
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance(int i) {
        FavoriteFragment fragment;
        fragment = new FavoriteFragment();

        return fragment;
    }
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Added requireNonNull
        View view = Objects.requireNonNull(inflater).inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mApiService = DI.getNeighbourApiService();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        //Added requireNonNull
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }
    @Override
    public void onResume() {
        // method to reload fragment each time user navigates on it
        // //(...not really eventually cf.remove favorite from NeighborRecycler)
        super.onResume();
        initList();
    }
    private void initList() {

        mFavorite = mApiService.getFavorites();
        mRecyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(mFavorite));
    }

    //onStart - onStop and @Subscribe to register as Subscriber of the event
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    //Added @NotNull and disabled Inspection for @param
    @Subscribe
    public void onDeleteFavorite(@NotNull DeleteFavoriteEvent event) {
        mApiService.deleteFavorite(event.favorite);
        initList();
    }
    /**
     * Fired if the user clicks on a delete button
     * Delete also corresponding favorite
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        Objects.requireNonNull(mRecyclerView.getAdapter()).notifyDataSetChanged();
    }
}

