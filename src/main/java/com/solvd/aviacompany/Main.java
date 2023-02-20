package com.solvd.aviacompany;

import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.utils.menu.MenuService;

public class Main {
    public static void main(String[] args) {
        ConnectionPool.getInstance().init();
        //new DataFactory().createData(); //Генератор один раз отработал, больше его запускать не нужно.
        MenuService menuService = new MenuService();
        menuService.menu();
//        ArrayList<Flight> flights = new ArrayList<>();
//        City Brest = new City(2, "Brest", new Country());
//        City Minsk = new City(1, "Minsk", new Country());
//        City Mogilev = new City(3, "Mogilev", new Country());
//        Flight f1 = new Flight(1, Minsk, Brest , 200, 1000);
//        Flight f2 = new Flight(2, Brest , Mogilev, 200, 1200);
//        Flight f3 = new Flight(3, Mogilev, Minsk, 300, 500);
//        flights.add(f1);
//        flights.add(f2);
//        IntIntPair[][] mas = new PairGraphBuilder().getMatrixFromList(flights);
//        List<IntIntPair> weights = new ArrayList<>();
    }
}