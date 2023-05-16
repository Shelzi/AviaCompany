package com.solvd.aviacompany.service.impl;

import com.solvd.aviacompany.db.dao.ITicketDao;
import com.solvd.aviacompany.db.dao.impl.TicketDaoImpl;
import com.solvd.aviacompany.hierarchy.Ticket;
import com.solvd.aviacompany.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private static final ITicketDao iTicketDao = new TicketDaoImpl();

    @Override
    public boolean addTicket(Ticket ticket) {
        return iTicketDao.create(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return iTicketDao.read();
    }
}
