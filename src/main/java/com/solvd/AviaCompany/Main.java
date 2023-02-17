package com.solvd.AviaCompany;

import com.solvd.AviaCompany.utils.DataFactory;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.impl.FlightGraphService;
import com.solvd.AviaCompany.utils.menu.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new DataFactory().createData();
        MenuService menuService = new MenuService();
        //menuService.menu();
        ArrayList<Flight> flights = new ArrayList<>();
        City Brest = new City(2, "Brest", 2);
        City Minsk = new City(1, "Minsk", 2);
        City Mogilev = new City(3, "Mogilev", 2);
        Flight f1 = new Flight(1, Minsk, Brest , 200, 1000);
        Flight f2 = new Flight(2, Brest , Mogilev, 200, 1200);
        Flight f3 = new Flight(3, Mogilev, Minsk, 300, 500);
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        int[][] mas = new FlightGraphService().getMatrixFromList(flights);
        for(int[] i : mas){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }

    }
}