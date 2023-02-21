package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Passenger {
    @XmlAttribute
    private int id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;

    @Override
    public String toString() {
        return "Passenger id " + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
