package com.solvd.aviacompany.utils.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuService {
    private final Logger logger = LogManager.getLogger();
    private MenuOptions currentOption = MenuOptions.ALL;
    private GetDao.AvailableOptions choice = null;
    private final CaseAll caseAll = new CaseAll();
    private final CaseBookFlight caseBookFlight = new CaseBookFlight();
    private final CaseExit caseExit = new CaseExit();
    private final CasePrint casePrint = new CasePrint();
    private final GetDao getDao = new GetDao();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        AtomicBoolean exit = new AtomicBoolean(false);
        logger.info("HI");
        while (!exit.get()) {
            switch (currentOption) {
                case ALL -> {
                    currentOption = caseAll.all(sc);
                }
                case PRINT -> {
                    currentOption = casePrint.print(sc, choice, getDao);
                }
                case PRINT_FLIGHTS -> {
                    currentOption = casePrint.printFlights();
                }
                case BOOK_FLIGHT -> {
                    currentOption = caseBookFlight.bookFlight(sc, choice);
                }
                case EXIT -> {
                    currentOption = caseExit.exit(sc, exit);
                }
                default -> {
                    logger.fatal("INVALID ENUM CASE");
                    throw new RuntimeException("INVALID ENUM CASE");
                }
            }
        }
        sc.close();
    }
}
