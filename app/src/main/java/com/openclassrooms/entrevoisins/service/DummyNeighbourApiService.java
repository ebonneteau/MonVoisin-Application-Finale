package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.ArrayList;
import java.util.List;


/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavorites() {

        return favorites;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favorites.remove(neighbour);
    }

    @Override
    public void addFavorite(Neighbour favorite) {
        favorites.add(favorite);
    }

    @Override
    public void deleteFavorite(Neighbour favorite) {
        favorites.remove(favorite);
    }


    @Override
    public void addNeighbour() {
        neighbours.add(DummySupNeighbourGenerator.getDummySupNeighbours());

    }


}
