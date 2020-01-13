package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user wants to add a Neighbour object into Favorite List (second callBack addFavoriteEvent)
 */
public class GetFavoriteEvent {
    public Neighbour neighbour;


    /**
     * Neighbour to get
     */
    public GetFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;


    }


}
