package com.solvd.AviaCompany.hierarchy;

import com.solvd.AviaCompany.service.impl.IntIntPair;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ComplexRoute {
    private List<City> cities;
    private List<IntIntPair> weights;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < cities.size(); i++){
            res.append(cities.get(i).getName());
            if(i != cities.size() - 1)
                res.append(" -> ");
        }
        IntIntPair total = getTotalWeight();
        res.append("\n      Cost: ").append(total.getA()).append(" $    Distance: ").append(total.getB()).append(" km");
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexRoute that = (ComplexRoute) o;
        return Objects.equals(cities, that.cities) && Objects.equals(weights, that.weights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cities, weights);
    }

    public List<Ticket> getFlights(Passenger passenger){
        if(cities.isEmpty())
            return null;
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0; i < cities.size() - 1; i++){
            Flight f = new Flight(0, cities.get(i), cities.get(i + 1), weights.get(i).getA(), weights.get(i).getB());
            Ticket t = new Ticket(passenger, f);
            tickets.add(t);
        }
        return tickets;
    }

    public IntIntPair getTotalWeight(){
        if(cities.isEmpty())
            return null;
        IntIntPair res = new IntIntPair(0, 0);
        for(IntIntPair w : weights){
            res.sumTwoPairs(w);
        }
        return res;
    }
}
