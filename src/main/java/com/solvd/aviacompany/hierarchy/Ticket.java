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
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {
    @XmlAttribute
    private int id;
    @XmlElement
    private Passenger passenger;
    @XmlElement
    private Flight flight;

    @Override
    public String toString() {
        return "Ticket id=" + id +
                ". " + passenger +
                ". " + flight;
    }
}
