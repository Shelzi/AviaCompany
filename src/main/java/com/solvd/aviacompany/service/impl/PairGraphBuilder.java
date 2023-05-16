package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.Flight;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PairGraphBuilder {
    private static final IntIntPair INF = new IntIntPair(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);

    public IntIntPair[][] getMatrixFromList(List<Flight> flightList,
                                            Map<String, Integer> cityNameToIndexMap,
                                            Map<Integer, City> cityIndextoCityMap) {
        // Create a map from city IDs to city objects
        Map<Integer, City> cityMap = new TreeMap<>();
        for (Flight flight : flightList) {
            cityMap.put(flight.getDeparture().getId(), flight.getDeparture());
            cityMap.put(flight.getDestination().getId(), flight.getDestination());
        }

        // Create a map from city objects to their indices in the matrix

        int index = 0;
        Map<City, Integer> cityIndexMap = new LinkedHashMap<>();
        for (City city : cityMap.values()) {
            cityIndexMap.put(city, index);
            cityIndextoCityMap.put(index, city);
            cityNameToIndexMap.put(city.getName(), index);
            index++;
        }

        // Create the distance matrix
        IntIntPair[][] matrix = new IntIntPair[cityMap.size()][cityMap.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = new IntIntPair(0, 0);
                } else {
                    matrix[i][j] = INF;
                }
            }
        }

        // Set the distances for pairs of cities
        for (Flight flight : flightList) {
            int i = cityIndexMap.get(flight.getDeparture());
            int j = cityIndexMap.get(flight.getDestination());
            matrix[i][j] = new IntIntPair(flight.getCost(), flight.getDistance());
        }
        return matrix;
    }
}
