package com.solvd.AviaCompany.hierarchy;

import lombok.Data;

import java.util.List;

@Data
public class Country {

    private int id;
    private String name;
    private List<City> cities;
}
