package com.solvd.aviacompany.utils.menu;

import com.solvd.aviacompany.db.dao.IBaseDao;
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
}
