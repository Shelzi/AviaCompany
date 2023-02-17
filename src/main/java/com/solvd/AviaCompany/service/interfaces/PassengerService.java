package com.solvd.AviaCompany.service.interfaces;

import com.solvd.AviaCompany.hierarchy.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    List<Passenger> getAllPassengers();

    Optional<Passenger> getPassengerById(int id);

    Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName);

    boolean addPassenger(Passenger passenger);

    Optional<Passenger> updatePassenger(Passenger passenger);
}
