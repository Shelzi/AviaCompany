package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.impl.FlightDAOImpl;
import com.solvd.AviaCompany.hierarchy.City;
import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.service.FlightService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {
    List<Flight> flights;
    {
        flights = new FlightDAOImpl().read();
        flights.sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                int res;
                if (o1.getId() == o2.getId())
                    res = 0;
                else if (o1.getId() < o2.getId())
                    res = 1;
                else
                    res = -1;
                return res;
            }
        });
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public Optional<Flight> getFlightById(int id){
        return flights.stream().filter((x) -> x.getId() == id).findAny();
    }
}
