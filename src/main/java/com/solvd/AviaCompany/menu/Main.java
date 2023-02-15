package com.solvd.AviaCompany.menu;

import com.mysql.cj.log.Log;
import com.solvd.AviaCompany.menu.menuUtils.CaseALL;
import com.solvd.AviaCompany.menu.menuUtils.CaseEXIT;
import com.solvd.AviaCompany.menu.menuUtils.CasePRINT;
import com.solvd.AviaCompany.menu.menuUtils.GetDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static MenuOptions currentOption = MenuOptions.ALL;
    private static final GetDAO.AvailableOptions choice = null;

    public enum MenuOptions{
        ALL,
        PRINT,
        ADD,
        UPDATE,
        DELETE,
        BOOK_FLIGHT,
        EXIT;
    }

    public static void main(String[] args) {
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
                    //currentOption = CaseBOOK_FLIGHT.delete(LOGGER, sc, choice);
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