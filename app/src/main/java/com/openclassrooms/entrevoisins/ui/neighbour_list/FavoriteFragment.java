package com.openclassrooms.entrevoisins.ui.neighbour_list;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private NeighbourApiService mApiService;
    private List<Favorite> mFavorite;
    private RecyclerView mRecyclerView;
    /**
     * Create and return a new instance
     * @return @{@link FavoriteFragment}
     * @param i
     */
    public static FavoriteFragment newInstance(int i) { //TODO Create FavoriteFragment
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,
                container, false);
        return view;
    }

    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.main_content);
        view.setText(item);
    }
}

