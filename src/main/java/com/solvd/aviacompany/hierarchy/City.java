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
@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {
    @XmlAttribute
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private Country country;

    @Override
    public String toString() {
        return name + " , country: " + country.getName();
    }
}
