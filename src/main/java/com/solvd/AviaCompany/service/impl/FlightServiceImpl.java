package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.impl.FlightDAOImpl;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {
    public List<Flight> getFlights() {
        List<Flight> flights = new FlightDAOImpl().read();
        flights.sort((o1, o2) -> Integer.compare(o2.getId(), o1.getId()));
        return flights;
    }

    public Optional<Flight> getFlightById(int id) {
        return new FlightDAOImpl().read().stream()
                .filter(x -> x.getId() == id)
                .findAny();
    }
}