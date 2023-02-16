package com.solvd.AviaCompany.utils.menu;

import com.solvd.AviaCompany.utils.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuService {
    private final Logger LOGGER = LogManager.getLogger(Main.class);
    private MenuOptions currentOption = MenuOptions.ALL;
    private GetDAO.AvailableOptions choice = null;
    private final CaseALL caseALL = new CaseALL();
    private final CaseBOOK_FLIGHT caseBOOK_FLIGHT = new CaseBOOK_FLIGHT();
    private final CaseEXIT caseEXIT = new CaseEXIT();
    private final CasePRINT casePRINT = new CasePRINT();
    private final GetDAO getDAO = new GetDAO();



    public void menu() {
        Scanner sc = new Scanner(System.in);
        getDAO.setLOGGER(LOGGER);
        AtomicBoolean exit = new AtomicBoolean(false);
        while (!exit.get()) {
            switch (currentOption) {
                case ALL -> {
                    currentOption = caseALL.all(LOGGER, sc);
                }
                case PRINT -> {
                    currentOption = casePRINT.print(LOGGER, sc, choice, getDAO);
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
                    currentOption = caseBOOK_FLIGHT.book(LOGGER, sc, choice);
                }
                case EXIT -> {
                    currentOption = caseEXIT.exit(LOGGER, sc, exit);
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
