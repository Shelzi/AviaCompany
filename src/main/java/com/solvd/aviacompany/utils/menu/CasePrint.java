package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.db.dao.IBaseDao;
import com.solvd.aviacompany.db.dao.impl.FlightDaoImpl;
import com.solvd.aviacompany.hierarchy.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class CasePrint {
    private static final Logger logger = LogManager.getLogger();
    public MenuOptions print(Scanner sc, GetDao.AvailableOptions choice, GetDao getDao) {
        MenuOptions currentOption =  MenuOptions.ALL;
        IBaseDao<?> dao = getDao.getDao(sc);
        if (dao != null) {
            logger.info("""
                                
                    (1)  * PRINT ALL
                    (2)  * PRINT BY ID
                    
                    (0)  * BACK
                    """);
            boolean validInt = false;
            int k = -1;
            k = ScannerGetter.getInt(sc, 0, 2);
            switch (k) {
                case 1 -> {
                    List<?> objects = dao.read();
                    for(var v : objects)
                        logger.info(v.toString());

                }
                case 2 -> {
                    logger.info(" ENTER ID:");
                    k = sc.nextInt();
                    logger.info(dao.read(k));
                }
                default -> currentOption =  MenuOptions.PRINT;
            }
        }
        return currentOption;
    }

    public MenuOptions printFlights(){
        IBaseDao<Flight> flightIBaseDao = new FlightDaoImpl();
        List<Flight> flights = flightIBaseDao.read();
        if(flights.isEmpty()){
            logger.info("Sorry we don't have any available flights right now");
        }
        else{
            logger.info("Here are all flights we have right now:");
            for(Flight f : flights){
                logger.info(f.toString());
            }
        }
        return MenuOptions.ALL;
    }
}
