package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "city")
public class City {
    private int id;
    private String name;
    private Country country;

    @Override
    public String toString() {
        return name + " , country: " + country.getName();
    }
}
