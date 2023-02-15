package com.solvd.AviaCompany.menu.menuUtils;

import com.solvd.AviaCompany.db.DAO;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.menu.Main;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CaseBOOK_FLIGHT {
    private static List<Flight> flightList;

    static{
        //flightList = FlightDAO.getAll();
    }

    private static Optional<Flight> FloydShortest(String from, String to){
        return Optional.of(null);
    }

    private static Optional<Flight> FloydCheapest(String from, String to){
        return Optional.of(null);
    }

    public static Main.MenuOptions book(Logger LOGGER, Scanner sc, GetDAO.AvailableOptions choice) {
        Main.MenuOptions currentOption = Main.MenuOptions.ALL;
        String from, to;
        LOGGER.info(" FROM:");
        from = ScannerGetter.getString(sc);
        LOGGER.info(" TO:");
        to = ScannerGetter.getString(sc);

        Optional<Flight> shortest = FloydShortest(from, to);
        shortest.ifPresent(flight -> LOGGER.info(" Shortest Flight: " + flight.toString()));
        Optional<Flight> cheapest = FloydCheapest(from, to);
        cheapest.ifPresent(flight -> LOGGER.info(" Cheapest Flight: " + flight.toString()));

        return currentOption;
    }
}
