package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();



    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
}
/**
 * Favorite API client
 */
 interface FavoriteApiService { //TODO Create Class FavoriteApiService.java copy and paste interface and set it to public

    /**
     * Get all my Favorites
     * @return {@link List}
     */
    List<Neighbour> getNeighbours(Neighbour favorite);}
