package com.solvd.AviaCompany.menu.menuUtils;

import com.solvd.AviaCompany.menu.Main;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CaseEXIT {

    public static Main.MenuOptions exit(Logger LOGGER, Scanner sc, AtomicBoolean exit) {
        Main.MenuOptions currentOption = Main.MenuOptions.ALL;
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
                currentOption = Main.MenuOptions.ALL;
            }
        }
        return currentOption;
    }

}
