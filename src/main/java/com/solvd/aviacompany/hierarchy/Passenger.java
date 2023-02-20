package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {
    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Passenger id " + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
