package com.solvd.aviacompany;

import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.utils.menu.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        try{
            ConnectionPool.getInstance().init();
        }catch (Exception e){
            logger.fatal("Houston, looks like we've got DB problem");
        }
        MenuService menuService = new MenuService();
        menuService.menu();
    }
}