package com.solvd.AviaCompany.hierarchy;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class Flight {
    private City departure;
    private City destination;
    private int cost;
    private int distance;

    private List<Passenger> passengerList = new ArrayList<>();

    @Override
    public String toString() {
        return "Flight{\n" +
                "        departure = '" + departure + "\'\n" +
                "        destination = '" + destination + "\'\n" +
                "        cost = " + cost + "$\n" +
                "        distance = " + distance + " km\n";

    }
}
