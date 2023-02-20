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
        return iFlightDao.read();
    }

    @Override
    public Optional<Flight> getFlightById(int id){
        return iFlightDao.read(id);
    }
}
