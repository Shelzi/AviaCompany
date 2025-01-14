package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;

import java.util.*;

public class FlightGraphService {

    public int[][] getMatrixFromList(List<Flight> flightList) {

        // Create a map from city IDs to city objects
        Map<Integer, City> cityMap = new HashMap<>();
        for (Flight flight : flightList) {
            cityMap.put(flight.getDeparture().getId(), flight.getDeparture());
            cityMap.put(flight.getDestination().getId(), flight.getDestination());
        }

        // Create a map from city objects to their indices in the matrix
        Map<City, Integer> cityIndexMap = new HashMap<>();
        int index = 0;
        for (City city : cityMap.values()) {
            cityIndexMap.put(city, index++);
        }

        // Create the distance matrix
        int[][] matrix = new int[cityMap.size()][cityMap.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }

        // Set the distances for pairs of cities
        for (Flight flight : flightList) {
            int i = cityIndexMap.get(flight.getDeparture());
            int j = cityIndexMap.get(flight.getDestination());
            matrix[i][j] = flight.getDistance();
        }

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == Integer.MAX_VALUE / 2) {
//                    System.out.printf("%-5s", "inf");
//                } else {
//                    System.out.printf("%-5d", matrix[i][j]);
//                }
//            }
//            System.out.println();
//        }
        return matrix;
    }
}
