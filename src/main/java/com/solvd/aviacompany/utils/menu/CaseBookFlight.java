package com.solvd.aviacompany.utils.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.ComplexRoute;
import com.solvd.aviacompany.hierarchy.Passenger;
import com.solvd.aviacompany.hierarchy.Ticket;
import com.solvd.aviacompany.service.impl.*;
import com.solvd.aviacompany.utils.xml.Jaxb;
import com.solvd.aviacompany.utils.xml.XmlTicket;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CaseBookFlight {
    private final FlightServiceImpl flightService = new FlightServiceImpl();
    private final CityServiceImpl cityService = new CityServiceImpl();

    private final PassengerServiceImpl passengerService = new PassengerServiceImpl();
    private static final Logger logger = LogManager.getLogger();

    private Optional<ComplexRoute> floydShortest(String from, String to, boolean distance) {
        Map<String, Integer> cityNameToIndexMap = new LinkedHashMap<>();
        Map<Integer, City> cityIndextoCityMap = new LinkedHashMap<>();
        IntIntPair[][] graph = new PairGraphBuilder()
                .getMatrixFromList(flightService.getFlights(),
                        cityNameToIndexMap,
                        cityIndextoCityMap);
        List<IntIntPair> weights = new ArrayList<>();
        if (cityIndextoCityMap.isEmpty() || cityNameToIndexMap.isEmpty()) {
            return Optional.empty();
        }
        Integer fromId = cityNameToIndexMap.get(from);
        Integer toId = cityNameToIndexMap.get(to);
        if (fromId == null || toId == null) {
            return Optional.empty();
        }
        List<Integer> ids = new FloydPairs().findPath(graph, fromId, toId, distance, weights);
        List<City> cities = cityService.mapIndexListToCity(ids, cityIndextoCityMap);
        return Optional.of(new ComplexRoute(cities, weights));
    }

    private Optional<ComplexRoute> floydCheapest(String from, String to) {
        return floydShortest(from, to, true);
    }

    public MenuOptions bookFlight(Scanner sc, GetDao.AvailableOptions choice) {
        MenuOptions currentOption;

        String from, to;
        boolean stillChoosing;
        boolean buyingTicket;
        ComplexRoute flightToBuy;
        logger.info("Please enter passenger info:");
        logger.info("First name:");
        String firstName = ScannerGetter.getString(sc);
        logger.info("Last name:");
        String lastName = ScannerGetter.getString(sc);
        Optional<Passenger> passengerOptional = passengerService.getPassengerByFirstAndLastName(firstName, lastName);
        Passenger p;
        if (passengerOptional.isEmpty()) {
            p = Passenger.builder().firstName(firstName).lastName(lastName).build();
            passengerService.addPassenger(p);
            p = passengerService.getPassengerByFirstAndLastName(firstName, lastName).get();
        } else {
            p = passengerOptional.get();
        }


        do {
            flightToBuy = null;
            stillChoosing = false;
            buyingTicket = false;
            logger.info(" FROM:");
            from = ScannerGetter.getString(sc);
            logger.info(" TO:");
            to = ScannerGetter.getString(sc);
            Optional<ComplexRoute> shortest = floydShortest(from, to, false);
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
                logger.info("Great! Tickets for passenger " + p.getFirstName() + " " + p.getLastName() +
                        " have been written to file Tickets.json [.xml], have a great fly");
                IntIntPair wei = flightToBuy.getTotalWeight();
                List<Ticket> tickets = flightToBuy.getFlights(p);
                TicketServiceImpl ticketDao = new TicketServiceImpl();
                for (Ticket t : tickets) {
                    ticketDao.addTicket(t);
                }
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String json = objectMapper.writeValueAsString(new XmlTicket(tickets, wei));
                    FileUtils.write(new File("Tickets.json"), json,
                            StandardCharsets.UTF_8);
                } catch (IOException e) {
                    logger.warn(e.getMessage());
                }
                try {
                    new Jaxb().marshalTickets(tickets, wei, "Tickets.xml");
                } catch (JAXBException | IOException e) {
                    logger.warn(e.getMessage());
                }
            }
        }
        while (stillChoosing);
        currentOption = MenuOptions.ALL;
        return currentOption;
    }
}
