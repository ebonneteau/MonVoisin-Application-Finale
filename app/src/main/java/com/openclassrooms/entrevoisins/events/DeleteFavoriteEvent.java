package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Favorite;


/**
 * Event fired when a user deletes a favorite
 */
public class DeleteFavoriteEvent {


    /**
     * Favorite to delete
     */
    public Favorite favorite;

    /**
     * Constructor.
     * @param favorite
     */
    public DeleteFavoriteEvent(Favorite favorite) {
        this.favorite = favorite;
    }

}
