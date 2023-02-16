package com.solvd.AviaCompany.db.tablecolumns;

public enum CountryColumn {

    ID("id"),
    NAME("name");

    private String column;

    CountryColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
