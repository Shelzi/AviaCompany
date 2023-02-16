package com.solvd.AviaCompany.hierarchy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Flight {
    private int departCityId;
    private int destinCityId;
    private int cost;
    private int distance;

}
