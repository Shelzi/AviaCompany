package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.IPassengerDAO;
import com.solvd.aviacompany.db.dao.impl.PassengerDAOImpl;
import com.solvd.aviacompany.hierarchy.Passenger;
import com.solvd.aviacompany.service.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PassengerServiceImpl implements PassengerService {

    private static final Logger logger = LogManager.getLogger(PassengerServiceImpl.class);
    private final IPassengerDAO iPassengerDAO = new PassengerDAOImpl();;

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengerList = iPassengerDAO.read();
        passengerList.sort(Comparator.comparingInt(Passenger::getId));
        return passengerList;
    }

    public Optional<Passenger> getPassengerById(int id) {
        return Optional.ofNullable(iPassengerDAO.read(id));
    }

    public Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName) {
        return Optional.ofNullable(iPassengerDAO.getPassengerByFirstAndLastName(firstName, lastName));
    }

    public boolean addPassenger(Passenger passenger) {
        return iPassengerDAO.create(passenger);
    }

    public Optional<Passenger> updatePassenger(Passenger passenger) {
        Passenger updatedPassenger = iPassengerDAO.update(passenger);
        if (updatedPassenger == null) {
            return Optional.empty();
        } else {
            return Optional.of(updatedPassenger);
        }
    }
}
