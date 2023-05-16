package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.IFlightDao;
import com.solvd.aviacompany.db.dao.impl.FlightDaoImpl;
import com.solvd.aviacompany.hierarchy.City;
import com.solvd.aviacompany.hierarchy.Country;
import com.solvd.aviacompany.hierarchy.Flight;
import com.solvd.aviacompany.service.FlightService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private final IFlightDao iFlightDao = new FlightDaoImpl();

    @Override
    public List<Flight> getFlights() {
        //List<Flight> flightList = iFlightDao.read();
/*        List<Flight> flightList = new ArrayList<>(List.of());
        City brest = new City(3, "brest", new Country());
        City minsk = new City(1, "minsk", new Country());
        City gomel = new City(2, "gomel", new Country());
        Flight f1 = new Flight(1, minsk, brest , 100, 1000);
        Flight f2 = new Flight(2, brest , gomel, 200, 1200);
        Flight f3 = new Flight(3, gomel, minsk, 300, 500);
        flightList.add(f1);
        flightList.add(f2);
        flightList.add(f3);*/
        //flightList.sort(Comparator.comparingInt(Flight::getId));
        return iFlightDao.read();
    }

    @Override
    public Optional<Flight> getFlightById(int id){
        return iFlightDao.read(id);
    }
}
