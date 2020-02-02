package com.openclassrooms.entrevoisins.service;
import com.openclassrooms.entrevoisins.model.Favorite;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.model.SupNeighbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Favorite> favorites = new ArrayList<>();
    private List<SupNeighbour> supNeighbour = DummySupNeighbourGenerator.DUMMY_SUP_NEIGHBOURS;



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Favorite> getFavorites() {

            return favorites;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
    }
    @Override
    public void deleteFavorite(Favorite favorite){
        favorites.remove(favorite);
    }

    @Override
    public List<SupNeighbour> GetSupNeighbor() {
        Random rand = new Random();
        return Collections.singletonList(supNeighbour.get(rand.nextInt(supNeighbour.size())));

    }



}
