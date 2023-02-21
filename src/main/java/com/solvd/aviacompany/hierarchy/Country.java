package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
    @XmlAttribute
    private int id;
    @XmlElement
    private String name;

    @Override
    public String toString() {
        return "id = " + id + "  " + name;
    }
}
