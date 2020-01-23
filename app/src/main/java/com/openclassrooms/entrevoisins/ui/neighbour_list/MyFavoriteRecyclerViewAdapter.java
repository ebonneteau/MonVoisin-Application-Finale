package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Favorite;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//TODO Creation of this RecyclerView

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private List<Favorite> mFavorite;


    private static final String TAG = "MyFavRView";


    public MyFavoriteRecyclerViewAdapter(List<Favorite> items) {
        mFavorite = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);
        Log.d(TAG, "Size of list Favorite in  Favorite recyclerViewAdapter: " + mFavorite.size());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Favorite favorite = mFavorite.get(position);
        holder.mFavoriteName.setText(favorite.getName());
        Glide.with(holder.mFavoriteAvatar.getContext())
                .load(favorite.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavoriteAvatar);


        holder.mFavoriteAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//method to view favorite details
                Log.d(TAG, "onClick: Favorite to view details");
                Intent intent = new Intent(holder.mFavoriteAvatar.getContext(), NeighbourDetail.class);
                intent.putExtra("avatar_Url", favorite.getAvatarUrl());
                intent.putExtra("item_list_avatar", favorite.getName());
                intent.putExtra("item_list_id", (Integer) favorite.getId());

                holder.mFavoriteAvatar.getContext().startActivity(intent);
            }
        });
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFavorite.remove(favorite);
                Log.d(TAG, "Size of list Favorite: " + mFavorite.size());
                notifyDataSetChanged(); //This refresh the fragment list view automatically

            }
        });
    }


    @Override
    public int getItemCount() {
        return mFavorite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mFavoriteAvatar;
        @BindView(R.id.item_list_name)
        public TextView mFavoriteName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
