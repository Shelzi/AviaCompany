package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.dao.IFlightDAO;
import com.solvd.AviaCompany.db.impl.FlightDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Country;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.interfaces.FlightService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private IFlightDAO iFlightDAO;

    public FlightServiceImpl() {
        this.iFlightDAO = new FlightDAOImpl();
    }


    @Override
    public List<Flight> getFlights() {
        //List<Flight> flightList = iFlightDAO.read();
        List<Flight> flightList = new ArrayList<>(List.of());
        City Brest = new City(3, "Brest", new Country());
        City Minsk = new City(1, "Minsk", new Country());
        City Gomel = new City(2, "Gomel", new Country());
        Flight f1 = new Flight(1, Minsk, Brest , 100, 1000);
        Flight f2 = new Flight(2, Brest , Gomel, 200, 1200);
        Flight f3 = new Flight(3, Gomel, Minsk, 300, 500);
        flightList.add(f1);
        flightList.add(f2);
        flightList.add(f3);
        //flightList.sort(Comparator.comparingInt(Flight::getId));
        return flightList;
    }

    @Override
    public Optional<Flight> getFlightById(int id){
        return Optional.ofNullable(iFlightDAO.read(id));
    }
}
