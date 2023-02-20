package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    private int id;
    private City departure;
    private City destination;
    private int cost;
    private int distance;


    @Override
    public String toString() {
        return "Flight{\n" +
                "        id = " + id + "\n" +
                "        departure = " + departure + "\n" +
                "        destination = " + destination + "\n" +
                "        cost = " + cost + " $\n" +
                "        distance = " + distance + " km\n";

    }
}
