package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Country;
import com.solvd.AviaCompany.hierarchy.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairGraphBuilder {
    private static final IntIntPair INF = new IntIntPair(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
    //private static final IntIntPair INF = new IntIntPair(-2, -2);
    public IntIntPair[][] getMatrixFromList(List<Flight> flightList) {

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

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(INF)){
                    System.out.printf(" %-10s ", "inf");
                } else {
                    System.out.printf(" %-10s ", matrix[i][j].toString());
                }
            }
            System.out.println();
        }
        return matrix;
    }

    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();
        City Brest = new City(2, "Brest", new Country());
        City Minsk = new City(1, "Minsk", new Country());
        City Mogilev = new City(3, "Mogilev", new Country());
        Flight f1 = new Flight(1, Minsk, Brest , 200, 1000);
        Flight f2 = new Flight(2, Brest , Mogilev, 200, 1200);
        Flight f3 = new Flight(3, Mogilev, Minsk, 300, 500);
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        IntIntPair[][] mas = new PairGraphBuilder().getMatrixFromList(flights);
        System.out.println();
        new FloydPairs().testCase(mas);
    }
}
