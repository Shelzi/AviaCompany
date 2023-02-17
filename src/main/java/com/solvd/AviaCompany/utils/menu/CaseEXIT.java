package com.solvd.AviaCompany.utils.menu;

import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CaseEXIT {

    public MenuOptions exit(Logger LOGGER, Scanner sc, AtomicBoolean exit) {
         MenuOptions currentOption =  MenuOptions.ALL;
        LOGGER.info("\n     * ARE YOU SURE (y/n):");
        char ch = 's';
        do {
            String s = sc.nextLine().trim();
            if (s.length() != 0)
                ch = Character.toLowerCase(s.charAt(0));
        } while (ch != 'y' && ch != 'n');
        switch (ch) {
            case 'y' -> {
                LOGGER.info("     * BYE...");
                exit.set(true);
            }
            case 'n' -> {
                //currentOption =  MenuOptions.ALL;
            }
        }
        return currentOption;
    }

}
