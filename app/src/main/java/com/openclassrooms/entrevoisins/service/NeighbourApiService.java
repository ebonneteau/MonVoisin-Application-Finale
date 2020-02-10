package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get favorite Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getFavorites();


    /**
     * Deletes a neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * add a favorite
     */
    void addFavorite(Neighbour favorite);

    void deleteFavorite(Neighbour favorite);


    void addNeighbour();


}


