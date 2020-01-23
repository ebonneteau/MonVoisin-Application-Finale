package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Favorite;
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
     * Get favorite Neighbours
     * @return {@link List}
     */
    List<Favorite> getFavorites();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    /**
     * add a favorite
     *
     */
    void addFavorite (Favorite favorite);

    /**
     * delete a favorite
     *
     */
    void deleteFavorite (Favorite favorite);

}


