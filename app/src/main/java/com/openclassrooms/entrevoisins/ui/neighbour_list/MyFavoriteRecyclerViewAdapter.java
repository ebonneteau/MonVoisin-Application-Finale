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
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private List<Neighbour> mFavorite;

    MyFavoriteRecyclerViewAdapter(List<Neighbour> items) {
        mFavorite = items;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour favorite = mFavorite.get(position);
        holder.mFavoriteName.setText(favorite.getName());
        Glide.with(holder.mFavoriteAvatar.getContext())
                .load(favorite.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavoriteAvatar);

        //method Lambda to view favorite details
        holder.mFavoriteAvatar.setOnClickListener(v -> {

            Intent intent = new Intent(holder.mFavoriteAvatar.getContext(), NeighbourDetail.class);
            intent.putExtra("avatar_Url", favorite.getAvatarUrl());
            intent.putExtra("item_list_avatar", favorite.getName());
            intent.putExtra("item_list_id", favorite.getId());

            holder.mFavoriteAvatar.getContext().startActivity(intent);
        });
        holder.mDeleteButton.setOnClickListener(v -> {
            EventBus.getDefault().post(new DeleteFavoriteEvent(favorite));

            // This method refreshes the fragment list view automatically
            // Needed only when not using EventBus (ex: direct List manipulation)
            // notifyDataSetChanged();
            // Keeping this for personal records

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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
