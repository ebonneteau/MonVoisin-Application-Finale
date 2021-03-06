package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;


public class NeighbourDetail extends AppCompatActivity { //This is a Scrolling Activity Android Studio type

    private NeighbourApiService mApiService;
    private int mListId;
    private String mAvatarUrl;
    private String mAvatarName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back button with option requireNonNull
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getNeighbourApiService();
        getIncomingIntent();

        //Verify if Neighbor is already part of Favorite list
        //If it's the case, then favorite button on will be shown as ticked (full yellow)
        //Else this button wil be shown as un-ticked (yellow border only)
        //Yellow color overrides white color in xml file
        FloatingActionButton mFab = findViewById(R.id.add_to_favorite_bt);
        if (mApiService.getFavorites().contains(new Neighbour(mListId, mAvatarName, mAvatarUrl))) {
            mFab.setImageResource(R.drawable.ic_star_white_24dp);

        } else {
            mFab.setImageResource(R.drawable.ic_star_border_white_24dp);

        }
        mFab.setOnClickListener(view -> {

            mApiService = DI.getNeighbourApiService();
            //add to, or remove from Favorite list
            //on star button  clicking
            if (mApiService.getFavorites().contains(new Neighbour(mListId, mAvatarName, mAvatarUrl))) {

                mFab.setImageResource(R.drawable.ic_star_white_24dp);
                mApiService.deleteFavorite(new Neighbour(mListId, mAvatarName, mAvatarUrl));
                Snackbar.make(view, mAvatarName + " is removed from your favorites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mFab.setImageResource(R.drawable.ic_star_border_white_24dp);

            } else {
                mFab.setImageResource(R.drawable.ic_star_border_white_24dp);
                mApiService.addFavorite(new Neighbour(mListId, mAvatarName, mAvatarUrl));
                Snackbar.make(view, mAvatarName + " is added to your favorites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mFab.setImageResource(R.drawable.ic_star_white_24dp);
            }
        });
    }

    //Method for home button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingIntent() {
        // This method rescues
        // neighbor avatar image
        // neighbor name
        // neighbor list id (UID)
        // from the contextual click of one neighbor in MyNeighbourRecyclerViewAdapter
        // or from MyFavoriteRecyclerViewAdapter

        if (getIntent().hasExtra("avatar_Url") && getIntent().hasExtra("item_list_avatar")) {
            String avatarUrl = getIntent().getStringExtra("avatar_Url");
            mAvatarUrl = avatarUrl;
            String avatarName = getIntent().getStringExtra("item_list_avatar");
            mAvatarName = avatarName;
            Integer neighbourListId = getIntent().getIntExtra("item_list_id", mListId);
            mListId = neighbourListId;
            //Call method to inject neighbor image (avatarUrl)
            setImage(avatarUrl);
            //Call method to inject avatarName as title of collapsingBar
            //as well as other needed values
            setAllNeededValues(avatarName);

        }
    }

    private void setImage(String avatarUrl) {

        ImageView avatarImage = findViewById(R.id.item_list_avatar);
        Glide.with(this)
                .asBitmap()
                .load(avatarUrl)
                .into(avatarImage);

    }

    private void setAllNeededValues(String avatarName) {

        // By default the CollapsingToolbarLayout name displayed, is the app name.
        // In this case it is "Neighbour Detail"
        // This method displays the name of the neighbor.
        // instead of default title.
        CollapsingToolbarLayout myTitleBar = findViewById(R.id.toolbar_layout);
        myTitleBar.setTitle(avatarName);
        //add name as title into 1st card
        TextView mCardViewTitle = findViewById(R.id.neighbor_detail_name);
        mCardViewTitle.setText(avatarName);
        //add name-ville as living location
        TextView mCardViewLivingLocation = findViewById(R.id.living_location_text);
        mCardViewLivingLocation.setText(String.format("%s-Ville", avatarName));
        //add name-phone number as phone number
        TextView mCardViewPhoneNumber = findViewById(R.id.phone_number_text);
        mCardViewPhoneNumber.setText(String.format("%s-Phone number", avatarName));
        //add name@facebook.com as facebook address
        TextView mCardViewFacebookAddress = findViewById(R.id.facebook_address_text);
        mCardViewFacebookAddress.setText(String.format("%s@facebook.com", avatarName));
    }
}
