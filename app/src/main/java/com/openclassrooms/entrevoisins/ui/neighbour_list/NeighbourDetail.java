package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;




public class NeighbourDetail extends AppCompatActivity { //This is a Scrolling Activity Android Studio type
    private static final String TAG = "NeighbourDetail";
    private NeighbourApiService mApiService;
    private int mListId;
    private String mAvatarurl;
    private String mAvatarName;






    @SuppressLint("RestrictedApi") //enable to use
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: started ");

        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Verify if Neighbor is already part of Favorite list
        //If it's the case, then favorite button on will be shown
        //Clicking on it will suppress the neighbor from favorite list
        //Then, button will turn to off view
        mApiService = DI.getNeighbourApiService();
        getIncomingIntent();

                FloatingActionButton mFab =  findViewById(R.id.add_to_favorite_bt);
        if (mApiService.getFavorites().contains( new Favorite(mListId,mAvatarName,mAvatarurl))){
            mFab.setImageResource(R.drawable.ic_star_white_24dp);
        }else {
            mFab.setImageResource(R.drawable.ic_star_border_white_24dp);

        }
                mFab.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        //add to favorite
                        //when star button is clicked

                        Log.d(TAG, "Value of mListId: " + mListId);
                        Log.d(TAG, "Value of mAvatarName: " + mAvatarName);
                        Log.d(TAG, "Value of mAvatarurl: " + mAvatarurl);
                        //Verify list favorite size

                        mApiService = DI.getNeighbourApiService();
                        int favoriteListSize = mApiService.getFavorites().size();



                        //if (favoriteListSize == 0) {
                        //    mApiService.addFavorite(new Favorite(mListId, mAvatarName, mAvatarurl));
                        //    favoriteListSize = mApiService.getFavorites().size();
                        //    Log.d(TAG, "Size of Favorite list: " + favoriteListSize);
                        //    Snackbar.make(view, "This is your first added favorite !!", Snackbar.LENGTH_LONG)
                        //            .setAction("Action", null).show();

                        //}
                        if (mApiService.getFavorites().contains( new Favorite(mListId,mAvatarName,mAvatarurl))) {
                            //create a new object
                            //per existing index
                            //if object is not null
                            //compare object
                            mFab.setImageResource(R.drawable.ic_star_white_24dp);
                            mApiService.deleteFavorite(new Favorite (mListId, mAvatarName, mAvatarurl) );
                            Snackbar.make(view, "removing favorite !!!!!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            mFab.setImageResource(R.drawable.ic_star_border_white_24dp);


                        } else {
                            mFab.setImageResource(R.drawable.ic_star_border_white_24dp);
                            mApiService.addFavorite(new Favorite(mListId, mAvatarName, mAvatarurl));
                            //favoriteListSize = mApiService.getFavorites().size();
                            Log.d(TAG, "Size of Favorite list: " + favoriteListSize);
                            Snackbar.make(view, "you added favorite number: " + favoriteListSize, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            mFab.setImageResource(R.drawable.ic_star_white_24dp);

                        }

                    }
                });
                getIncomingIntent();
        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            Log.d(TAG, "Clicked on home button: ");

        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingIntent() {
        // This method rescues
        // neighbor avatar image
        // neighbor name
        // neighbor list id (UID)
        // from the contextual click of one neighbor in MyNeighbourRecyclerViewAdapter

        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if (getIntent().hasExtra("avatar_Url") && getIntent().hasExtra("item_list_avatar")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String avatarUrl = getIntent().getStringExtra("avatar_Url");
            //Verify the good url is returned and stored
            Log.d(TAG, "getIncomingIntent avatarUrl: " + avatarUrl);
            mAvatarurl = avatarUrl;
            Log.d(TAG, "mAvatarurl has now value of " + mAvatarurl);

            String avatarName = getIntent().getStringExtra("item_list_avatar");
            //Verify the good name is returned and stored
            Log.d(TAG, "getIncomingIntent avatarName: " + avatarName);
            mAvatarName = avatarName;
            Log.d(TAG, "mAvatarName has now value of " + mAvatarName);

            Integer neighbourListId = getIntent().getIntExtra("item_list_id", mListId);
            //Verify the good id is returned and stored
            Log.d(TAG, "getIncomingIntent: " + neighbourListId);
            mListId = neighbourListId;
            Log.d(TAG, "mListId has now value of " + mListId);

            setImage(avatarUrl); //Call method to inject neighbor image (avatarUrl)
            setAllNeededValues(avatarName);
            //Call method to inject avatarName as title of collapsingBar
            //as well as other needed values
        }
    }

    private void setImage(String avatarUrl) {

        Log.d(TAG, "setImage: setting the image and name to widgets.");
        ImageView avatarImage = findViewById(R.id.item_list_avatar);

        Glide.with(this)
                .asBitmap()
                .load(avatarUrl)
                .into(avatarImage);

    }

    private void setAllNeededValues (String avatarName) {

        // By default the CollapsingToolbarLayout name displayed, is the app name.
        // In this case it is "Neighbor Detail"
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
