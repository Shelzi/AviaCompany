package com.solvd.aviacompany.service;

import com.solvd.aviacompany.hierarchy.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    List<Passenger> getAllPassengers();

    Optional<Passenger> getPassengerById(int id);

    Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName);

    boolean addPassenger(Passenger passenger);

    boolean updatePassenger(Passenger passenger);
}
