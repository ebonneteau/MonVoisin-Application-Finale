package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.SupNeighbour;


import java.util.Arrays;
import java.util.List;


public abstract class DummySupNeighbourGenerator {

    public static List<SupNeighbour> DUMMY_SUP_NEIGHBOURS = Arrays.asList(
            new SupNeighbour(13, "Andrea", "https://i.pravatar.cc/"),
            new SupNeighbour(14, "Marilyne", "https://i.pravatar.cc/"),
            new SupNeighbour(15, "Armando", "https://i.pravatar.cc/"),
            new SupNeighbour(16, "Vicenzo", "https://i.pravatar.cc/"),
            new SupNeighbour(17, "Ursula", "https://i.pravatar.cc/"),
            new SupNeighbour(18, "Yvonne", "https://i.pravatar.cc/")

    );



}
