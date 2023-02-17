package com.solvd.AviaCompany.db.tablecolumns;

public enum FlightColumn {

    ID("id"),
    DEPARTURE("dep_city_id"),
    DESTINATION("dest_city_id"),
    COST("cost"),
    DISTANCE("distance");

   private String column;

   FlightColumn(String column){
       this.column = column;
   }

   public String getColumn(){
       return column;
   }
}
