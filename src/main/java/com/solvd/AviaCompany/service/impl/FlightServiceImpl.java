package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.dao.IFlightDAO;
import com.solvd.AviaCompany.db.impl.FlightDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.interfaces.FlightService;

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
        List<Flight> flightList = iFlightDAO.read();
        flightList.sort(Comparator.comparingInt(Flight::getId));
        return flightList;
    }

    @Override
    public Optional<Flight> getFlightById(int id){
        return Optional.ofNullable(iFlightDAO.read(id));
    }
}
