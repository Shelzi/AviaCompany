package com.solvd.aviacompany.hierarchy;

import com.solvd.aviacompany.service.impl.IntIntPair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexRoute {
    private List<City> cities;
    private List<IntIntPair> weights;

    public List<Ticket> getFlights(Passenger passenger){
        if(cities.isEmpty())
            return new ArrayList<>();
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
}
