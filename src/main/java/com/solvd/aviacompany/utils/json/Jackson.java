package com.solvd.AviaCompany.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.AviaCompany.hierarchy.Passenger;
import org.apache.logging.log4j.LogManager;

public class Jackson {

    public static void main(String[] args) {
        Passenger passenger = new Passenger(1, "Vlad", "Zhuravlev");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValueAsString(passenger);
        } catch (JsonProcessingException e) {
            LogManager.getLogger(Jackson.class).fatal(e.getMessage());
        }
    }

}
