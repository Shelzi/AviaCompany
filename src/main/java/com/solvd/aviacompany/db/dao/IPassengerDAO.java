package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Passenger;

public interface IPassengerDAO extends IBaseDAO<Integer, Passenger> {
    Passenger getPassengerByFirstAndLastName(String firstName, String lastName);
}
