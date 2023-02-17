package com.solvd.AviaCompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private int id;
    private City departure;
    private City destination;
    private int cost;
    private int distance;

    @Override
    public String toString() {
        return "Flight{\n" +
                "        departure = '" + departure + "\'\n" +
                "        destination = '" + destination + "\'\n" +
                "        cost = " + cost + "$\n" +
                "        distance = " + distance + " km\n";

    }
}
