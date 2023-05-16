package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.db.dao.IBaseDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GetDao {
    public enum AvailableOptions {
        AIRPORTS,
        FLIGHTS,
        PILOTS,
        PLANES,
        TICKETS,
        BACK
    }

    private final Logger logger = LogManager.getLogger();
    private GetDao.AvailableOptions choice = GetDao.AvailableOptions.BACK;
    public GetDao.AvailableOptions getChoice() {
        return choice;
    }

    public IBaseDao getDao(Scanner sc) {
        logger.info("""
                                
                (1)  * AIRPORTS
                (2)  * FLIGHTS
                (3)  * PILOTS
                (4)  * PLANES
                (5)  * TICKETS

                (0)  * BACK
                """);
        IBaseDao dao = null;
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
                //dao = new AirportDao();
                choice = AvailableOptions.AIRPORTS;
            }
            case 2 -> {
                //dao = (myBatis) ? new FlightService() : new FlightDao();
                choice = AvailableOptions.FLIGHTS;
            }
            case 3 -> {
                //dao = (myBatis) ? new PilotService() : new PilotDao();
                choice = AvailableOptions.PILOTS;
            }
            case 4 -> {
                //dao = (myBatis) ? new PlaneService() : new PlaneDao();
                choice = AvailableOptions.PLANES;
            }
            case 5 -> {
                //dao = (myBatis) ? new TicketService() : new TicketDao();
                choice = AvailableOptions.TICKETS;
            }

            default -> {
                choice = AvailableOptions.BACK;
                //currentOption = Main.Main.MenuOptions.ALL;
            }
        }
        return dao;
    }
}
