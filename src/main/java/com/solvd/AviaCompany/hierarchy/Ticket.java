package com.solvd.AviaCompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private Passenger passenger;
    private Flight flight;

}
