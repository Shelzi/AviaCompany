package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.db.dao.IBaseDao;
import com.solvd.aviacompany.db.dao.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GetDao {
    public enum AvailableOptions {
        CITIES,
        FLIGHTS,
        COUNTRIES,
        PASSENGERS,
        TICKETS,
        BACK
    }

    private final Logger logger = LogManager.getLogger();
    private GetDao.AvailableOptions choice = GetDao.AvailableOptions.BACK;
    public GetDao.AvailableOptions getChoice() {
        return choice;
    }

    public IBaseDao<?> getDao(Scanner sc) {
        logger.info("""
                                
                (1)  * CITIES
                (2)  * COUNTRIES
                (3)  * PASSENGERS

                (0)  * BACK
                """);
        IBaseDao<?> dao = null;
        int k = -1;
        boolean validInt = false;
        k = ScannerGetter.getInt(sc, 0, 3);
        switch (k) {
            case 1 -> {
                dao = new CityDaoImpl();
                choice = AvailableOptions.CITIES;
            }
            case 2 -> {
                dao = new CountryDaoImpl();
                choice = AvailableOptions.COUNTRIES;
            }
            case 3 -> {
                dao = new PassengerDaoImpl();
                choice = AvailableOptions.PASSENGERS;
            }
            default -> {
                choice = AvailableOptions.BACK;
                //currentOption = Main.Main.MenuOptions.ALL;
            }
        }
        return dao;
    }
}
