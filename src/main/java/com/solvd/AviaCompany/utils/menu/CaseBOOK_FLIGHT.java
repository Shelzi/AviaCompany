package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.hierarchy.Ticket;
import com.solvd.AviaCompany.utils.Main;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CaseBOOK_FLIGHT {
    private List<Flight> flightList;

    {
        //flightList = FlightDAO.getAll();
    }

    private Optional<Flight> FloydShortest(String from, String to) {
        return Optional.ofNullable(null);
    }

    private Optional<Flight> FloydCheapest(String from, String to) {
        return Optional.ofNullable(null);
    }

    public MenuOptions book(Logger LOGGER, Scanner sc, GetDAO.AvailableOptions choice) {
        MenuOptions currentOption;

        String from, to;
        boolean stillChoosing;
        boolean buyingTicket = false;
        Flight flightToBuy = null;

        do {
            stillChoosing = false;
            buyingTicket = false;
            LOGGER.info(" FROM:");
            from = ScannerGetter.getString(sc);
            LOGGER.info(" TO:");
            to = ScannerGetter.getString(sc);

            Optional<Flight> shortest = FloydShortest(from, to);
            if (shortest.isEmpty()) {
                LOGGER.info(" WE ARE SORRY, but there is no available route " + from + " to " + to);
                currentOption = MenuOptions.ALL;
                return currentOption;
            }

            Optional<Flight> cheapest = FloydCheapest(from, to);
            if (cheapest.equals(shortest)) {
                LOGGER.info(" We have only one available route for you: " + shortest.toString());
                LOGGER.info(""" 
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
                        LOGGER.info(" GREAT! WHAT IS YOUR ROTE");
                        stillChoosing = true;
                    }
                    default -> {
                        currentOption = MenuOptions.ALL;
                    }
                }
            } else {
                shortest.ifPresent(flight -> LOGGER.info(" *  Shortest Flight: " + flight.toString()));
                cheapest.ifPresent(flight -> LOGGER.info(" *  Cheapest Flight: " + flight.toString()));
                LOGGER.info(""" 
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
                        LOGGER.info(" GREAT! WHAT IS YOUR ROTE");
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
