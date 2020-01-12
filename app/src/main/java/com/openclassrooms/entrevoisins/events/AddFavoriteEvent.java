package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Favorite;

/**
 * Event fired when a user deletes a Neighbour
 */
public class AddFavoriteEvent {

    /**
     * Neighbour to delete
     */
    public Favorite favorite;

    /**
     * Constructor.
     * @param neighbour
     */
    public AddFavoriteEvent(Favorite neighbour) {
        this.favorite = neighbour;
    }

}
