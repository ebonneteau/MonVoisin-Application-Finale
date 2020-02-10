package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
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
        //Given
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.addFavorite(neighbourToDelete);
        //When
        service.deleteNeighbour(neighbourToDelete);
        //Assert
        assertFalse(service.getNeighbours().contains(neighbourToDelete));

        assertFalse(service.getFavorites().contains(neighbourToDelete));

    }
    // Added test - EB
    @Test
    public void addNeighbourWithSuccess() {
        int neighbourListSize = service.getNeighbours().size();
        //This method get a random Neighbor from hard coded list in DummySupNeighbourGenerator
        service.addNeighbour();
        // we ensure that the size of neighbourListSize get only 1 more element
        assert ( service.getNeighbours().size() == neighbourListSize +1);

    }


}
