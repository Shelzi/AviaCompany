package com.solvd.AviaCompany.db.dao;

import com.solvd.AviaCompany.hierarchy.Passenger;

public interface IPassengerDAO extends IBaseDAO<Integer, Passenger> {
    Passenger getPassengerByFirstAndLastName(String firstName, String lastName);
}
