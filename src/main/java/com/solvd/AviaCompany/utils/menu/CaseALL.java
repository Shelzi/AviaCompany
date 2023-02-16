package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.Main;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CaseALL {

    public static Main.MenuOptions all(Logger LOGGER, Scanner sc) {
        Main.MenuOptions currentOption = Main.MenuOptions.ALL;
        LOGGER.info("""
                                        
                (1)  * PRINT
                (2)  * ADD
                (3)  * UPDATE
                (4)  * DELETE
                (5)  * BOOK_FLIGHT
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
        } while (!validInt || c < 0 || c > 5);

        switch (c) {
            case 1 -> currentOption = Main.MenuOptions.PRINT;
            case 2 -> currentOption = Main.MenuOptions.ADD;
            case 3 -> currentOption = Main.MenuOptions.UPDATE;
            case 4 -> currentOption = Main.MenuOptions.DELETE;
            case 5 -> currentOption = Main.MenuOptions.BOOK_FLIGHT;

            case 0 -> currentOption = Main.MenuOptions.EXIT;
            default -> currentOption = Main.MenuOptions.ALL;
        }
        return currentOption;
    }
}
