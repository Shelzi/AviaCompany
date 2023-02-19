package com.solvd.aviacompany.db.tablecolumns;

public enum TicketColumn {

    PASSENGER("passengers_id"),
    FLIGHT("flights_id");
    private String column;

    TicketColumn(String column){
        this.column = column;
    }

    public String getColumn(){
        return column;
    }
}
