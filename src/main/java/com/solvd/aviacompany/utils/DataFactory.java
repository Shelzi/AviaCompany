package com.solvd.aviacompany.utils;

import com.solvd.aviacompany.db.dao.impl.CityDaoImpl;
import com.solvd.aviacompany.db.dao.impl.CountryDaoImpl;
import com.solvd.aviacompany.db.dao.impl.FlightDaoImpl;
import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.hierarchy.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataFactory {
    public void createData() {
        //createCountriesTable();
        //createCitiesTable();
        //createFlightsTable();
        //Генератор один раз отработал, больше его запускать не нужно.
    }

    private void createCountriesTable() {
        Map<Integer, String> countriesMap = new HashMap<>() {{
            put(1, "Belarus");
            put(2, "Lithuania");
            put(3, "Latvia");
            put(4, "Austria");
            put(5, "Great Britain");
            put(6, "Germany");
            put(7, "Spain");
            put(8, "Italy");
            put(9, "France");
            put(10, "Czech Republic");
        }};
        CountryDaoImpl countryDao = new CountryDaoImpl();

        for (Map.Entry<Integer, String> entry : countriesMap.entrySet()) {
            Country country = new Country();
            country.setId(entry.getKey());
            country.setName(entry.getValue());
            countryDao.create(country);
        }
    }

    private void createCitiesTable() {
        Map<String, Integer> citiesMap = new HashMap<>() {{
            put("Minsk", 1);
            put("Vilnius", 2);
            put("Riga", 3);
            put("Vienna", 4);
            put("London", 5);
            put("Berlin", 6);
            put("Munich", 6);
            put("Frankfurt", 6);
            put("Madrid", 7);
            put("Rome", 8);
            put("Naples", 8);
            put("Paris", 9);
            put("Marseilles",9);
            put("Prague", 10);
        }};
        CityDaoImpl cityDao = new CityDaoImpl();

        for (Map.Entry<String, Integer> entry : citiesMap.entrySet()) {
            City city = new City();
            city.setName(entry.getKey());
            Country country = new Country();
            country.setId(entry.getValue());
            city.setCountry(country);
            cityDao.create(city);
        }
    }

    private void createFlightsTable() {
        int flightsNum = 10;
        FlightDaoImpl flightsDao = new FlightDaoImpl();
        CityDaoImpl cityDao = new CityDaoImpl();
        int[] costList = new int[]{3876, 4561, 2710, 5012, 2980, 4970, 3965, 3260, 4510, 2245};
        int[] distanceList = new int[]{1250, 1760, 1490, 1620, 1980, 2230, 1350, 2340, 1190, 1410};
        List<String> listOfPairs = new ArrayList<>();
        for (int i = 0; i < flightsNum; i++) {
            Flight flight = new Flight();
            int i1;
            int i2;
            Random random = new Random();
            do {
                i1 = random.nextInt(1, 15);
                i2 = random.nextInt(1, 15);
                String pair = Integer.toString(i1) + i2;
                if ((i1 != i2) && (!listOfPairs.contains(pair))) {
                    flight.setDeparture(cityDao.read(i1).get());
                    flight.setDestination(cityDao.read(i2).get());
                    listOfPairs.add(pair);
                }
            } while (i1 == i2);
            flight.setCost(costList[random.nextInt(10)]);
            flight.setDistance(distanceList[random.nextInt(10)]);
            flightsDao.create(flight);
        }
    }
}
