package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.ITicketDAO;
import com.solvd.aviacompany.db.impl.TicketDAOImpl;
import com.solvd.aviacompany.hierarchy.Ticket;
import com.solvd.aviacompany.service.interfaces.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private static final ITicketDAO iTicketDAO = new TicketDAOImpl();

    @Override
    public boolean addTicket(Ticket entity) {
        return iTicketDAO.create(entity);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return iTicketDAO.read();
    }
}
