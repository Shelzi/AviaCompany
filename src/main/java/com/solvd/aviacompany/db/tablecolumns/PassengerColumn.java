package com.solvd.AviaCompany.db.tablecolumns;

public enum PassengerColumn {

    ID("id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name");

    private String column;
    PassengerColumn(String column){
        this.column = column;
    }

    public String getColumn(){
        return column;
    }
}
