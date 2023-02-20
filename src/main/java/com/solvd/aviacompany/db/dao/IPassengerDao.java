package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Passenger;

import java.util.Optional;

public interface IPassengerDao extends IBaseDao<Passenger> {
    Optional<Passenger> getPassengerByFirstAndLastName(String firstName, String lastName);

    public int getAutoIncrement();
}
