package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user wants to add a Neighbour object into Favorite List
 */
public class AddFavoriteEvent {
    public Neighbour neighbour;


    /**
     * Neighbour to get
     */
    public AddFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;


    }


}
