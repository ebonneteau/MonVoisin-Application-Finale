package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSession;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;




public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private static final String TAG = "MyNeighbourRView";
    private NeighbourApiService mApiService;





    MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;

    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);


        holder.mDeleteButton.setOnClickListener(v -> {
            Integer mTempNeighbourId = neighbour.getId();
            String mTempNeighborName = neighbour.getName();
            String mTempNeighbourAvatarUrl = neighbour.getAvatarUrl();

            mApiService = DI.getNeighbourApiService();
            EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            if (!mApiService.getFavorites().isEmpty()){
            //Delete also the corresponding Favorite from favorite fragment view
            EventBus.getDefault().post(new DeleteFavoriteEvent(new Favorite(mTempNeighbourId,mTempNeighborName,mTempNeighbourAvatarUrl)));


            }

        });


        //method to view details of item click
        holder.mNeighbourAvatar.setOnClickListener(v -> {

            Log.d(TAG, "onClick: Neighbour to view details");

            Intent intent = new Intent(holder.mNeighbourAvatar.getContext(), NeighbourDetail.class);
            intent.putExtra("avatar_Url", neighbour.getAvatarUrl());
            intent.putExtra("item_list_avatar", neighbour.getName());
            intent.putExtra("item_list_id", neighbour.getId());

            //Launch NeighborDetails activity
            holder.mNeighbourAvatar.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
