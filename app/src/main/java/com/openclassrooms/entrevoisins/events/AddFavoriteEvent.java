package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Favorite;

/**
 * Event fired when a user wants to add a Neighbour object into Favorite List (second callBack addFavoriteEvent)
 */
public class AddFavoriteEvent {
    public Favorite favorite;


    /**
     * Neighbour to get
     */
    public AddFavoriteEvent(Favorite favorite) {
        this.favorite = favorite;


    }


}