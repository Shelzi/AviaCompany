package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.dao.IPassengerDAO;
import com.solvd.AviaCompany.db.impl.PassengerDAOImpl;
import com.solvd.AviaCompany.hierarchy.Passenger;
import com.solvd.AviaCompany.service.interfaces.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PassengerServiceImpl implements PassengerService {

    private static final Logger logger = LogManager.getLogger(PassengerServiceImpl.class);
    private IPassengerDAO iPassengerDAO;

    public PassengerServiceImpl() {
        this.iPassengerDAO = new PassengerDAOImpl();
    }

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
