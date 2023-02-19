package com.solvd.aviacompany.service.interfaces;

import com.solvd.aviacompany.hierarchy.Ticket;

import java.util.List;

public interface TicketService {
    boolean addTicket(Ticket entity);
    List<Ticket> getAllTickets();
}
