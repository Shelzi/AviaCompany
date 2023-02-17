package com.solvd.AviaCompany.service.interfaces;

import com.solvd.AviaCompany.hierarchy.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<Flight> getFlights();

    Optional<Flight> getFlightById(int id);
}
