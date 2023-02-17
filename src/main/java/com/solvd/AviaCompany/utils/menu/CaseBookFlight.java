package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.impl.CityServiceImpl;
import com.solvd.AviaCompany.service.impl.FlightGraphService;
import com.solvd.AviaCompany.service.impl.FlightServiceImpl;
import com.solvd.AviaCompany.service.impl.FloydWarshallPathfinderServiceImpl;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CaseBookFlight {
    private final FlightServiceImpl flightService = new FlightServiceImpl();
    private final CityServiceImpl cityService = new CityServiceImpl();

    private Optional<Flight> FloydShortest(Logger logger, String from, String to,boolean distance) {
        int[][] graph = new FlightGraphService().getMatrixFromList(flightService.getFlights());
        Optional<City> optionalFrom = cityService.getCityByName(from);
        if(optionalFrom.isEmpty()){
            logger.info(" Sorry, we don't fly from " + from);
            return Optional.ofNullable(null);
        }
        Optional<City> optionalTo = cityService.getCityByName(to);
        if(optionalTo.isEmpty()){
            logger.info(" Sorry we dont fly to " + to);
            return Optional.ofNullable(null);
        }
        int fromId = optionalFrom.get().getId(), toId = optionalTo.get().getId();
        List<Integer> ids = new FloydWarshallPathfinderServiceImpl().findPath(graph, fromId, toId);
        List<City> cityList = cityService.mapIdListToCity(ids);
        return Optional.ofNullable(null);
    }

    private Optional<Flight> FloydCheapest(Logger logger, String from, String to) {
        return Optional.ofNullable(null);
    }

    public MenuOptions book(Logger logger, Scanner sc, GetDao.AvailableOptions choice) {
        MenuOptions currentOption;

        String from, to;
        boolean stillChoosing;
        boolean buyingTicket = false;
        Flight flightToBuy = null;

        do {
            stillChoosing = false;
            buyingTicket = false;
            logger.info(" FROM:");
            from = ScannerGetter.getString(sc);
            logger.info(" TO:");
            to = ScannerGetter.getString(sc);

            Optional<Flight> shortest = FloydShortest(logger, from, to, true);
            if (shortest.isEmpty()) {
                logger.info(" We are sorry, but there is no available route from " + from + " to " + to);
                currentOption = MenuOptions.ALL;
                return currentOption;
            }

            Optional<Flight> cheapest = FloydCheapest(logger, from, to);
            if (cheapest.equals(shortest)) {
                logger.info(" We have only one available route for you: " + shortest);
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
                        logger.info(" GREAT! WHAT IS YOUR ROTE");
                        stillChoosing = true;
                    }
                    default -> {
                        currentOption = MenuOptions.ALL;
                    }
                }
            }
        }
        while (stillChoosing);

        if (buyingTicket)
            currentOption = MenuOptions.TICKET_CREATION;
        else
            currentOption = MenuOptions.ALL;
        return currentOption;
    }
}
