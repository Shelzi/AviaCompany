package com.solvd.aviacompany.utils.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CaseAll {
    private static final Logger logger = LogManager.getLogger();
    public MenuOptions all(Scanner sc) {
        MenuOptions currentOption = MenuOptions.ALL;
        logger.info("""
                                        
                (1)  * PRINT ADDITIONAL DATA
                (2)  * PRINT ALL FLIGHTS
                (3)  * BOOK_FLIGHT
                
                (0)  * EXIT
                """);
        boolean validInt = false;
        int c = -1;
        c = ScannerGetter.getInt(sc, 0, 3);
        switch (c) {
            case 1 -> currentOption = MenuOptions.PRINT;
            case 2 -> currentOption = MenuOptions.PRINT_FLIGHTS;
            case 3 -> currentOption = MenuOptions.BOOK_FLIGHT;
            case 0 -> currentOption = MenuOptions.EXIT;
            default -> currentOption = MenuOptions.ALL;
        }
        return currentOption;
    }
}
