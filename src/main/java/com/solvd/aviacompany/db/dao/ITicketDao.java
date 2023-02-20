package com.solvd.aviacompany.db.dao;

import com.solvd.aviacompany.hierarchy.Ticket;

public interface ITicketDao extends IBaseDao<Ticket> {
    public int getAutoIncrement();
}
