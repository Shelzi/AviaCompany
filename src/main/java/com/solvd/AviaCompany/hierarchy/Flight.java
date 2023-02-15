package com.solvd.AviaCompany.hierarchy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Flight {
    private String departure;
    private String destination;
    private int cost;
    private int distance;

    private Date departureDate;
    private TimeZone timeZone;

    private List<Passenger> passengerList = new ArrayList<>();

    public Flight(String departure, String destination, int cost, int distance, Date departureDate, TimeZone timeZone) {
        this.departure = departure;
        this.destination = destination;
        this.cost = cost;
        this.distance = distance;
        this.departureDate = departureDate;
        this.timeZone = timeZone;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD, hh:mm");
        sdf.setTimeZone(timeZone);
        return "Flight{\n" +
                "        departure = '" + departure + "\'\n" +
                "        destination = '" + destination + "\'\n" +
                "        cost = " + cost + "$\n" +
                "        distance = " + distance + " km\n" +
                "        departure date = " + sdf.format(departureDate) + " " + timeZone.getDisplayName() + " }";
    }
}
