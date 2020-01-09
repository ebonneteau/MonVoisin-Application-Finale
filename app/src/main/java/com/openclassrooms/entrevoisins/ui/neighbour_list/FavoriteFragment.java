package com.openclassrooms.entrevoisins.ui.neighbour_list;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

public class FavoriteFragment extends Fragment {
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

