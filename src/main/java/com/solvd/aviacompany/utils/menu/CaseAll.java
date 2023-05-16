package com.solvd.aviacompany.utils.menu;

import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CaseAll {
    public MenuOptions all(Logger logger, Scanner sc) {
        MenuOptions currentOption = MenuOptions.ALL;
        logger.info("""
                                        
                (1)  * PRINT
                (2)  * BOOK_FLIGHT
                
                (0)  * EXIT
                """);
        boolean validInt = false;
        int c = -1;
        do {
            try {
                c = Integer.parseInt(sc.nextLine());
                validInt = true;
            } catch (NumberFormatException e) {
                validInt = false;
            }
        } while (!validInt || c < 0 || c > 2);
        switch (c) {
            case 1 -> currentOption = MenuOptions.PRINT;
//            case 2 -> currentOption = MenuOptions.ADD;
//            case 3 -> currentOption = MenuOptions.UPDATE;
//            case 4 -> currentOption = MenuOptions.DELETE;
            case 2 -> currentOption = MenuOptions.BOOK_FLIGHT;
            case 0 -> currentOption = MenuOptions.EXIT;
            default -> currentOption = MenuOptions.ALL;
        }
        return currentOption;
    }
}
