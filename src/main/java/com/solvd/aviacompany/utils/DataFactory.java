package com.solvd.aviacompany.utils;

import com.solvd.aviacompany.db.impl.CityDAOImpl;
import com.solvd.aviacompany.db.impl.CountryDAOImpl;
import com.solvd.aviacompany.db.impl.FlightDAOImpl;
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
        createCountriesTable();
        createCitiesTable();
        createFlightsTable();
    }

    private void createCountriesTable() {
        Map<Integer, String> countriesMap = new HashMap<>() {{
            put(112, "Belarus");
            put(440, "Lithuania");
            put(428, "Latvia");
            put(40, "Austria");
            put(826, "Great Britain");
            put(276, "Germany");
            put(724, "Spain");
            put(380, "Italy");
            put(250, "France");
            put(203, "Czech Republic");
        }};
        CountryDAOImpl countryDAO = new CountryDAOImpl();

        for (Map.Entry<Integer, String> entry : countriesMap.entrySet()) {
            Country country = new Country();
            country.setId(entry.getKey());
            country.setName(entry.getValue());
            countryDAO.create(country);
        }
    }

    private void createCitiesTable() {
        Map<String, Integer> citiesMap = new HashMap<>() {{
            put("Minsk", 112);
            put("Vilnius", 440);
            put("Riga", 428);
            put("Vienna", 40);
            put("London", 826);
            put("Berlin", 276);
            put("Munich", 276);
            put("Frankfurt", 276);
            put("Madrid", 724);
            put("Rome", 380);
            put("Naples", 380);
            put("Paris", 250);
            put("Marseilles", 250);
            put("Prague", 203);
        }};
        CityDAOImpl cityDAO = new CityDAOImpl();

        for (Map.Entry<String, Integer> entry : citiesMap.entrySet()) {
            City city = new City();
            city.setName(entry.getKey());
            Country country = new Country();
            country.setId(entry.getValue());
            city.setCountry(country);
            cityDAO.create(city);
        }
    }

    private void createFlightsTable() {
        int flightsNum = 10;
        FlightDAOImpl flightsDAO = new FlightDAOImpl();
        CityDAOImpl cityDAO = new CityDAOImpl();
        int[] costList = new int[]{3876, 4561, 2710, 5012, 2980, 4970, 3965, 3260, 4510, 2245};
        int[] distanceList = new int[]{1250, 1760, 1490, 1620, 1980, 2230, 1350, 2340, 1190, 1410};
        List<String> listOfPairs = new ArrayList<>();
        for (int i = 0; i < flightsNum; i++) {
            Flight flight = new Flight();
            int i1;
            int i2;
            Random random = new Random();
            do {
                i1 = random.nextInt(14);
                i2 = random.nextInt(14);
                String pair = Integer.toString(i1) + i2;
                if ((i1 != i2) && (!listOfPairs.contains(pair))) {
                    flight.setDeparture(cityDAO.read(i1));
                    flight.setDestination(cityDAO.read(i2));
                    listOfPairs.add(pair);
                }
            } while (i1 == i2);
            flight.setCost(costList[random.nextInt(10)]);
            flight.setDistance(distanceList[random.nextInt(10)]);
            flightsDAO.create(flight);
        }
    }
}
