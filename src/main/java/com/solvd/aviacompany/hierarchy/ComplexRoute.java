package com.solvd.aviacompany.hierarchy;

import com.solvd.aviacompany.db.dao.IBaseDao;
import com.solvd.aviacompany.db.dao.impl.FlightDaoImpl;
import com.solvd.aviacompany.db.dao.pool.ConnectionPool;
import com.solvd.aviacompany.service.impl.FlightServiceImpl;
import com.solvd.aviacompany.service.impl.IntIntPair;
import com.solvd.aviacompany.service.impl.TicketServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexRoute {
    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "citiy")
    private List<City> cities;


    @XmlElementWrapper(name = "Weights")
    @XmlElement(name = "weight")
    private List<IntIntPair> weights;

    public List<Ticket> getFlights(Passenger passenger) {
        if (cities.isEmpty())
            return new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        FlightServiceImpl flightIBaseDao = new FlightServiceImpl();
        TicketServiceImpl ticketService = new TicketServiceImpl();
        int id = ticketService.getAutoIncrement() + 1;
        for (int i = 0; i < cities.size() - 1; i++) {
            Flight flight =
                    flightIBaseDao.getFlightByDepId(cities.get(i).getId(), cities.get(i + 1).getId(),
                            weights.get(i).getA(), weights.get(i).getB()).orElse(new Flight());
            Ticket ticket = Ticket.builder()
                    .id(id++)
                    .passenger(passenger)
                    .flight(flight)
                    .build();
            tickets.add(ticket);
        }
        return tickets;
    }

    public IntIntPair getTotalWeight() {
        if (cities.isEmpty())
            return null;
        IntIntPair res = new IntIntPair(0, 0);
        for (IntIntPair w : weights) {
            res.sumTwoPairs(w);
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cities.size(); i++) {
            res.append(cities.get(i).getName());
            if (i != cities.size() - 1)
                res.append(" -> ");
        }
        IntIntPair total = getTotalWeight();
        res.append("\n      Cost: ").append(total.getA()).append(" $    Distance: ").append(total.getB()).append(" km");
        return res.toString();
    }

}
