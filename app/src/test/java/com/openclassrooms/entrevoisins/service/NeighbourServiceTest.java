package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        //Given neighbour at position 1 is part of someone's favorite (so, in favorite list)
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.addFavorite(neighbourToDelete);
        //When deleting this neighbour
        service.deleteNeighbour(neighbourToDelete);
        //Assert this neighbour is removed from neighbour list AND favorite list
        //reminder: IRL, a member signing off the application, should not be part of anyone's favorites !
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        assertFalse(service.getFavorites().contains(neighbourToDelete));

    }

    @Test
    public void addNeighbourWithSuccess() {
        //Given the size of Neighbour list
        int neighbourListSize = service.getNeighbours().size();
        //Then using addNeighbour event
        service.addNeighbour();
        // Assert neighbor list get one and only one more entry
        assert ( service.getNeighbours().size() == neighbourListSize +1);

    }


}
