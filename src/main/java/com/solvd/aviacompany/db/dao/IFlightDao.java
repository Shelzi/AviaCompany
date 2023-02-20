package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Flight;

import java.util.Optional;

public interface IFlightDao extends IBaseDao<Flight> {
    public Optional<Flight> getFlightByDepId(int dep_id, int dest_id, int cost, int distance);
}
