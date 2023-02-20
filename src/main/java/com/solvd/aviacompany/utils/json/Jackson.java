package com.solvd.aviacompany.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.aviacompany.hierarchy.*;
import com.solvd.aviacompany.service.impl.IntIntPair;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class Jackson {
    public static void main(String[] args) {
        Country belarus = new Country(1, "Belarus");
        List<City> cities = List.of(new City(1, "Minsk", belarus), new City(2, "Vitebsk", belarus),
                new City(3, "Gomel", belarus));
        List<IntIntPair> weights = List.of(new IntIntPair(100, 1000), new IntIntPair(200, 1200),
                new IntIntPair(300, 1300));
        ComplexRoute complexRoute = new ComplexRoute(cities, weights);

        List<Ticket> tickets = complexRoute.getFlights(new Passenger(1,"Vlad", "Zzhuravlev"));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(tickets));
        } catch (JsonProcessingException e) {
            LogManager.getLogger(Jackson.class).fatal(e.getMessage());
        }
    }
}
