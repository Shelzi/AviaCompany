package com.solvd.aviacompany.service;

import com.solvd.aviacompany.hierarchy.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> getFlights();

    Optional<Flight> getFlightById(int id);

    public Optional<Flight> getFlightByDepId(int dep_id, int dest_id, int cost, int distance);
}
