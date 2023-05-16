package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.db.dao.IBaseDao;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CasePrint {
    public MenuOptions print(Logger logger, Scanner sc, GetDao.AvailableOptions choice, GetDao getDao) {
        MenuOptions currentOption =  MenuOptions.ALL;
        IBaseDao dao = getDao.getDao(sc);
        if (dao != null) {
            logger.info("""
                                
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
                    logger.info(dao.read());
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
}
