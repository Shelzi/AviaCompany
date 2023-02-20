package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    private int id;
    private Passenger passenger;
    private Flight flight;

    @Override
    public String toString() {
        return "Ticket id=" + id +
                ". " + passenger +
                ". " + flight;
    }
}
