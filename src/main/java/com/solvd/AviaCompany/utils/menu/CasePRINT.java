package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.db.DAO;
import com.solvd.AviaCompany.utils.Main;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CasePRINT {

    public MenuOptions print(Logger LOGGER, Scanner sc, GetDAO.AvailableOptions choice, GetDAO getDAO) {
         MenuOptions currentOption =  MenuOptions.ALL;

        DAO dao = getDAO.getDAO(sc);
        choice = getDAO.getChoice();
        if (dao != null) {
            LOGGER.info("""
                                
                    (1)  * PRINT ALL
                    (2)  * PRINT BY ID
                    
                    (0)  * BACK
                    """);
            boolean validInt = false;
            int k = -1;
            do {
                try {
                    k = Integer.parseInt(sc.nextLine());
                    validInt = true;
                } catch (NumberFormatException e) {
                    validInt = false;
                }
            } while (!validInt || k < 0 || k > 2);
            switch (k) {
                case 1 -> {
                    LOGGER.info(dao.read());
                }
                case 2 -> {
                    LOGGER.info(" ENTER ID:");
                    k = sc.nextInt();
                    LOGGER.info(dao.read(k));
                }
                default -> currentOption =  MenuOptions.PRINT;
            }
        }
        return currentOption;
    }
}
