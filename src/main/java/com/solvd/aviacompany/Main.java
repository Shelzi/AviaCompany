package com.solvd.aviacompany;

import com.solvd.aviacompany.db.dao.impl.FlightDaoImpl;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.utils.menu.MenuService;

public class Main {
    public static void main(String[] args) {
        ConnectionPool.getInstance().init();
        FlightDaoImpl flightDao = new FlightDaoImpl();
        System.out.println(flightDao.getFlightByDepId(5, 12, 4561, 2230));
        //new DataFactory().createData(); //Генератор один раз отработал, больше его запускать не нужно.
        MenuService menuService = new MenuService();
        menuService.menu();

    }
}