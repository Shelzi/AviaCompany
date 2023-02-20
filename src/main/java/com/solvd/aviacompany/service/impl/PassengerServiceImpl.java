package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.IPassengerDao;
import com.solvd.aviacompany.db.dao.impl.PassengerDaoImpl;
import com.solvd.aviacompany.hierarchy.Passenger;
import com.solvd.aviacompany.service.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PassengerServiceImpl implements PassengerService {
    private static final Logger logger = LogManager.getLogger();
    private final IPassengerDao iPassengerDao = new PassengerDaoImpl();

    public List<Passenger> getAllPassengers() {
        return iPassengerDao.read();
    }

    public Optional<Passenger> getPassengerById(int id) {
        return iPassengerDao.read(id);
    }

    public Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName) {
        return iPassengerDao.getPassengerByFirstAndLastName(firstName, lastName);
    }

    public boolean addPassenger(Passenger passenger) {
        return iPassengerDao.create(passenger);
    }

    public boolean updatePassenger(Passenger passenger) {
        return iPassengerDao.update(passenger);
    }

    public int getAutoIncrement() {
        return iPassengerDao.getAutoIncrement();
    }
}
