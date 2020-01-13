package com.openclassrooms.entrevoisins.ui.neighbour_list;

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
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private final List<Favorite> mFavorite;
    private static final String TAG = "MyNeighbourRView";


    public MyFavoriteRecyclerViewAdapter(List<Favorite> items) {
        mFavorite = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);
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


        holder.mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO add same method for add
                Log.d(TAG, "onClick: Favorite");
                EventBus.getDefault().postSticky(mFavorite);
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
        @BindView(R.id.item_list_add_button)
        public ImageButton mAddButton;
        public ImageView mFavorite;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
