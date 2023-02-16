package com.solvd.AviaCompany.utils;

import com.solvd.AviaCompany.utils.menu.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static MenuOptions currentOption = MenuOptions.ALL;
    private static final GetDAO.AvailableOptions choice = null;

    public enum MenuOptions {
        ALL,
        PRINT,
        ADD,
        UPDATE,
        DELETE,
        BOOK_FLIGHT,
        EXIT;
    }

    public static void main(String[] args) {
//        try {
//            TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
//            Flight flight = new Flight("Minsk", "Vilnus", 200, 500,
//                    new SimpleDateFormat("YYYY-MM-DD  hh:mm").parse("2023-02-10  12:00"), timeZone);
//            LOGGER.info(flight);
//        } catch (ParseException e) {
//            LOGGER.warn("INVALID DATE");
//        }
        new DataFactory().createData();
        Menu();
    }

    private static void Menu() {
        Scanner sc = new Scanner(System.in);
        GetDAO.setLOGGER(LOGGER);
        AtomicBoolean exit = new AtomicBoolean(false);
        while (!exit.get()) {
            switch (currentOption) {
                case ALL -> {
                    currentOption = CaseALL.all(LOGGER, sc);
                }
                case PRINT -> {
                    currentOption = CasePRINT.print(LOGGER, sc, choice);
                }
                case ADD -> {
                    //currentOption = CaseADD.add(LOGGER, sc, choice);
                }
                case UPDATE -> {
                    //currentOption = CaseUPDATE.update(LOGGER, sc, choice);
                }
                case DELETE -> {
                    //currentOption = CaseDELETE.delete(LOGGER, sc, choice);
                }
                case BOOK_FLIGHT -> {
                    currentOption = CaseBOOK_FLIGHT.book(LOGGER, sc, choice);
                }
                case EXIT -> {
                    currentOption = CaseEXIT.exit(LOGGER, sc, exit);
                }
                default -> {
                    LOGGER.fatal("INVALID ENUM CASE");
                    throw new RuntimeException("INVALID ENUM CASE");
                }
            }
        }
        sc.close();
    }
}