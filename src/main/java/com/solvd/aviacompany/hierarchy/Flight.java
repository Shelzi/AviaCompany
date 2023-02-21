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
@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {
    @XmlAttribute
    private int id;
    @XmlElement
    private City departure;
    @XmlElement
    private City destination;
    @XmlElement
    private int cost;
    @XmlElement
    private int distance;


    @Override
    public String toString() {
        return "Flight\n" +
                "        id = " + id + "\n" +
                "        departure = " + departure + "\n" +
                "        destination = " + destination + "\n" +
                "        cost = " + cost + " $\n" +
                "        distance = " + distance + " km\n";

    }
}
