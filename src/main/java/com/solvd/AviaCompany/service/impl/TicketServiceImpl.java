package com.solvd.AviaCompany.service.impl;

import com.solvd.AviaCompany.db.dao.ITicketDAO;
import com.solvd.AviaCompany.db.impl.TicketDAOImpl;
import com.solvd.AviaCompany.hierarchy.Ticket;
import com.solvd.AviaCompany.service.interfaces.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private ITicketDAO iTicketDAO;

    public TicketServiceImpl() {
        this.iTicketDAO = new TicketDAOImpl();
    }


    @Override
    public boolean addTicket(Ticket entity) {
        return iTicketDAO.create(entity);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return iTicketDAO.read();
    }
}
