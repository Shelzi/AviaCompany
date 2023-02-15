package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.db.DAO;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GetDAO {

    public enum AvailableOptions {
        AIRPORTS,
        FLIGHTS,
        PILOTS,
        PLANES,
        TICKETS,

        BACK
    }
    //  C : 57   I : 12
    private static Logger LOGGER;
    private static boolean myBatis = false;
    private static GetDAO.AvailableOptions choice = GetDAO.AvailableOptions.BACK;

    public static GetDAO.AvailableOptions getChoice() {
        return choice;
    }

    public static void setMyBatis(boolean f) {
        myBatis = f;
    }


    public static DAO getDAO(Scanner sc) {
        LOGGER.info("""
                                
                (1)  * AIRPORTS
                (2)  * FLIGHTS
                (3)  * PILOTS
                (4)  * PLANES
                (5)  * TICKETS

                (0)  * BACK
                """);
        DAO dao = null;
        int k = -1;
        boolean validInt = false;
        do {
            try {

                k = Integer.parseInt(sc.nextLine());
                validInt = true;
            } catch (NumberFormatException e) {
                validInt = false;
            }
        } while (!validInt || k < 0 || k > 5);
        switch (k) {
            case 1 -> {
                //dao = new AirportDAO();
                choice = AvailableOptions.AIRPORTS;
            }
            case 2 -> {
                //dao = (myBatis) ? new FlightService() : new FlightDAO();
                choice = AvailableOptions.FLIGHTS;
            }
            case 3 -> {
                //dao = (myBatis) ? new PilotService() : new PilotDAO();
                choice = AvailableOptions.PILOTS;
            }
            case 4 -> {
                //dao = (myBatis) ? new PlaneService() : new PlaneDAO();
                choice = AvailableOptions.PLANES;
            }
            case 5 -> {
                //dao = (myBatis) ? new TicketService() : new TicketDAO();
                choice = AvailableOptions.TICKETS;
            }

            default -> {
                choice = AvailableOptions.BACK;
                //currentOption = Main.Main.MenuOptions.ALL;
            }
        }
        return dao;
    }

    public static GetDAO setLOGGER(Logger LOGGER) {
        GetDAO.LOGGER = LOGGER;
        return new GetDAO();
    }
}
