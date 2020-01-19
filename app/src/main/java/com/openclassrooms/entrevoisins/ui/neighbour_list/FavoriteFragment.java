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
import android.widget.TextView;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;
import java.util.List;


public class FavoriteFragment extends Fragment {
    private NeighbourApiService mApiService;
    private List<Favorite> mFavorites;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     *
     * @param i
     * @return @{@link FavoriteFragment}
     */
    public static FavoriteFragment newInstance(int i) { //TODO Create FavoriteFragment
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override

    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initList();
        return view;
    }


    private void initList() {

        if (mFavorites != null){
            mFavorites = mApiService.getFavorites();
            mRecyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(mFavorites));
        }

    }

    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.main_content);
        view.setText(item);
        //Todo add recyclerview using api service


    }
}

