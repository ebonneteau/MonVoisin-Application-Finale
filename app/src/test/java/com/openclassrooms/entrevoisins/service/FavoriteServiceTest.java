package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class FavoriteServiceTest {

    private NeighbourApiService service;


    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        service.addFavorite(service.getNeighbours().get(0));
    }

    @Test
    public void getFavoriteWithSuccess() {


        // Given when then
        List<Neighbour> expectedFavorites = service.getFavorites();

        assertTrue(expectedFavorites.size() == 1);
    }

    @Test
    public void addFavoritesWithSuccess() {
        //Given we get a neighbour at position 2
        Neighbour oneNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(1);


        //When we Add this neighbour (position 2 )in Favorite list
        //Here we test the addFavorite event (for EventBus)
        service.addFavorite(oneNeighbour);

        // Then Favorite list size must be 2
        assertTrue(service.getFavorites().size() == 2);


    }

    @Test
    public void deleteFavoriteWithSuccess() {


        //Given we get favorite position 1
        Neighbour favoriteToDelete = service.getFavorites().get(0);
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavorites().contains(favoriteToDelete));
    }

}
