package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.Arrays;
import java.util.List;
import java.util.Random;


public abstract class DummySupNeighbourGenerator {

    public static List<Neighbour> DUMMY_SUP_NEIGHBOURS = Arrays.asList(
            new Neighbour(13, "Andrea", "https://i.pravatar.cc/150?img=48"),
            new Neighbour(14, "Marilyne", "https://i.pravatar.cc/150?img=47"),
            new Neighbour(15, "Armando", "https://i.pravatar.cc/150?img=51"),
            new Neighbour(16, "Vicenzo", "https://i.pravatar.cc/150?img=57"),
            new Neighbour(17, "Ursula", "https://i.pravatar.cc/150?img=46"),
            new Neighbour(18, "Yvonne", "https://i.pravatar.cc/150?img=30")

    );

    static Neighbour getDummySupNeighbours() {
        Random rand = new Random();
        return DUMMY_SUP_NEIGHBOURS.get(rand.nextInt(DUMMY_SUP_NEIGHBOURS.size()));

    }


}
