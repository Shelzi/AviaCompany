package com.solvd.aviacompany.utils.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CaseExit {
    private static final Logger logger = LogManager.getLogger();
    public MenuOptions exit(Scanner sc, AtomicBoolean exit) {
         MenuOptions currentOption =  MenuOptions.ALL;
        logger.info("\n     * ARE YOU SURE (y/n):");
        char ch = 's';
        do {
            String s = sc.nextLine().trim();
            if (s.length() != 0)
                ch = Character.toLowerCase(s.charAt(0));
        } while (ch != 'y' && ch != 'n');
        switch (ch) {
            case 'y' -> {
                logger.info("     * BYE...");
                exit.set(true);
            }
            case 'n' -> {
                //currentOption =  MenuOptions.ALL;
            }
        }
        return currentOption;
    }
}
