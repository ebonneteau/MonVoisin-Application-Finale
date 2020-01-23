package com.openclassrooms.entrevoisins.ui.neighbour_list;

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
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public class NeighbourDetail extends AppCompatActivity { //This is a Scrolling Activity Android Studio type
    private static final String TAG = "NeighbourDetail";
    private NeighbourApiService mApiService;
    private int mListId = 0;
    private String mAvatarurl;
    private String mAvatarName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: started ");

        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_to_favorite_bt);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //add here method to add to favorite
                //when star button is clicked
                Snackbar.make(view, "Added to favorite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d(TAG, "Value of mListId: " + mListId );
                Log.d(TAG, "Value of mAvatarName: " + mAvatarName );
                Log.d(TAG, "Value of mAvatarurl: " + mAvatarurl );
                //Verify list favorite size

                mApiService = DI.getNeighbourApiService();
                mApiService.addFavorite (new Favorite(mListId ,mAvatarName, mAvatarurl ));
                Log.d(TAG, "Size of Favorite list: " + mApiService.getFavorites().size() );

            }
        });
        getIncomingIntent();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
           this.finish();
            Log.d(TAG, "Clicked on home button: " );

        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingIntent(){
        // This method rescues
        // neighbor avatar image
        // neighbor name
        // neighbor list id
        // from the contextual click of one neighbor in MyNeighbourRecyclerViewAdapter

        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("avatar_Url") && getIntent().hasExtra("item_list_avatar")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String avatarUrl = getIntent().getStringExtra("avatar_Url");
                     //Verify the good url is returned and stored
            Log.d(TAG, "getIncomingIntent avatarUrl: " + avatarUrl );
            mAvatarurl = avatarUrl;
            Log.d(TAG, "mAvatarurl has now value of " + mAvatarurl );

            String avatarName = getIntent().getStringExtra("item_list_avatar");
            //Verify the good url is returned and stored
            Log.d(TAG, "getIncomingIntent avatarUrl: " + avatarName );
            mAvatarName = avatarName;
            Log.d(TAG, "mAvatarName has now value of " + mAvatarName );

            Integer neighbourListId = getIntent().getIntExtra("item_list_id", mListId);
            //Verify the good id is returned and stored
            Log.d(TAG, "getIncomingIntent: " + neighbourListId );
            mListId = neighbourListId;
            Log.d(TAG, "mListId has now value of " + mListId );



            setImage(avatarUrl);
            setNameOfCollapsingToolbar(avatarName); //Call method to inject avatarName as title of collapsingBar
        }
    }
    private void setImage(String avatarUrl){

        Log.d(TAG, "setImage: setting the image and name to widgets.");
        ImageView avatarImage = findViewById(R.id.item_list_avatar);

        Glide.with(this)
                .asBitmap()
                .load(avatarUrl)
                .into(avatarImage);

    }
    private void setNameOfCollapsingToolbar (String avatarName){

        // By default the CollapsingToolbarLayout name displayed, is the app name.
        // In this case it is "Neighbor Detail"
        // This method displays the name of the neighbor.
        // instead of default title.
        CollapsingToolbarLayout myTitleBar = findViewById(R.id.toolbar_layout);
        myTitleBar.setTitle(avatarName);

    }



}
