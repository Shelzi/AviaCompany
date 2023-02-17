package com.solvd.AviaCompany.service.interfaces;

import com.solvd.AviaCompany.hierarchy.Ticket;

import java.util.List;

public interface TicketService {

    boolean addTicket(Ticket entity);
    List<Ticket> getAllTickets();
}
