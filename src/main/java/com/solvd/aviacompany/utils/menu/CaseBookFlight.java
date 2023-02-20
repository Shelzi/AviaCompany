package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.ComplexRoute;
import com.solvd.aviacompany.hierarchy.Passenger;
import com.solvd.aviacompany.hierarchy.Ticket;
import com.solvd.aviacompany.service.impl.CityServiceImpl;
import com.solvd.aviacompany.service.impl.FlightServiceImpl;
import com.solvd.aviacompany.service.impl.FloydPairs;
import com.solvd.aviacompany.service.impl.IntIntPair;
import com.solvd.aviacompany.service.impl.PairGraphBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CaseBookFlight {
    private final FlightServiceImpl flightService = new FlightServiceImpl();
    private final CityServiceImpl cityService = new CityServiceImpl();
    private static final Logger logger = LogManager.getLogger();

    private Optional<ComplexRoute> floydShortest(String from, String to, boolean distance) {
        Map<String, Integer> cityNameToIndexMap = new LinkedHashMap<>();
        Map<Integer, City> cityIndextoCityMap = new LinkedHashMap<>();
        IntIntPair[][] graph = new PairGraphBuilder()
                .getMatrixFromList(flightService.getFlights(),
                        cityNameToIndexMap,
                        cityIndextoCityMap);
        List<IntIntPair> weights = new ArrayList<>();
        int fromId = cityNameToIndexMap.get(from);
        int toId = cityNameToIndexMap.get(to);
        if (fromId == -1 || toId == -1) {
            return Optional.empty();
        }
        List<Integer> ids = new FloydPairs().findPath(graph, fromId, toId, distance, weights);
        List<City> cities = cityService.mapIndexListToCity(ids, cityIndextoCityMap);
        return Optional.of(new ComplexRoute(cities, weights));
    }

    private Optional<ComplexRoute> floydCheapest(String from, String to) {
        return floydShortest(from, to, false);
    }

    public MenuOptions book(Scanner sc, GetDao.AvailableOptions choice) {
        MenuOptions currentOption;

        String from, to;
        boolean stillChoosing;
        boolean buyingTicket;
        ComplexRoute flightToBuy;

        do {
            flightToBuy = null;
            stillChoosing = false;
            buyingTicket = false;
            logger.info(" FROM:");
            from = ScannerGetter.getString(sc);
            logger.info(" TO:");
            to = ScannerGetter.getString(sc);
            //from = "London";
            //to = "Vienna";
            Optional<ComplexRoute> shortest = floydShortest(from, to, true);
            if (shortest.isEmpty() || shortest.get().getCities().isEmpty()) {
                logger.info(" We are sorry, but there is no available route from " + from + " to " + to);
                logger.info(""" 
                        Would you like to choose another route?
                        (1)  * YES
                        (0)  * EXIT
                        """);
                int c = ScannerGetter.getInt(sc, 0, 1);
                if (c == 1) {
                    stillChoosing = true;
                    continue;
                } else {
                    currentOption = MenuOptions.ALL;
                    return currentOption;
                }
            }
            Optional<ComplexRoute> cheapest = floydCheapest(from, to);
            if (cheapest.equals(shortest)) {
                logger.info(" We have only one available route for you: " + shortest.get().toString());
                logger.info(""" 
                        Would you like to book it ?
                        (1)  * YES
                        (2)  * CHOOSE ANOTHER ROUTE
                                            
                        (0)  * EXIT
                        """);
                int c = ScannerGetter.getInt(sc, 0, 2);
                switch (c) {
                    case 1 -> {
                        buyingTicket = true;
                        flightToBuy = shortest.get();
                    }
                    case 2 -> {
                        logger.info(" GREAT! WHAT IS YOUR ROTE");
                        stillChoosing = true;
                    }
                    default -> {
                        currentOption = MenuOptions.ALL;
                    }
                }
            } else {
                shortest.ifPresent(flight -> logger.info(" *  Shortest Flight: " + flight));
                cheapest.ifPresent(flight -> logger.info(" *  Cheapest Flight: " + flight));
                logger.info(""" 
                        What is your choice ?
                        (1)  * SHORTEST
                        (2)  * CHEAPEST
                        (3)  * CHOOSE ANOTHER ROUTE
                                            
                        (0)  * EXIT
                        """);
                int c = ScannerGetter.getInt(sc, 0, 3);
                switch (c) {
                    case 1 -> {
                        buyingTicket = true;
                        flightToBuy = shortest.get();
                    }
                    case 2 -> {
                        buyingTicket = true;
                        flightToBuy = cheapest.get();
                    }
                    case 3 -> {
                        logger.info(" GREAT! WHAT IS YOUR ROUTE");
                        stillChoosing = true;
                    }
                    default -> {
                        currentOption = MenuOptions.ALL;
                    }
                }
            }
            if (flightToBuy != null) {
                logger.info("Great then provide us passenger data:");
                logger.info("First Name:");
                String fname = ScannerGetter.getString(sc);
                logger.info("Last Name");
                String lname = ScannerGetter.getString(sc);
                Passenger p = new Passenger(1, fname, lname);
                List<Ticket> tickets = flightToBuy.getFlights(p);
            }
        }
        while (stillChoosing);
        currentOption = MenuOptions.ALL;
        return currentOption;
    }
}
