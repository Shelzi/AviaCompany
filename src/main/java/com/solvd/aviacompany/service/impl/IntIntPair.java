package com.solvd.aviacompany.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "flight info")
@XmlAccessorType(XmlAccessType.FIELD)
public class IntIntPair {
    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private int a;
    @XmlElement(name = "distance")
    @JsonProperty("distance")
    private int b;

    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }

    public void sumTwoPairs(IntIntPair o){
        a += o.a;
        b += o.b;
    }
}
