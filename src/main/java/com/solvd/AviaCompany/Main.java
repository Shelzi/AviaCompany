package com.solvd.AviaCompany;

import com.solvd.AviaCompany.utils.DataFactory;
import com.solvd.AviaCompany.utils.menu.*;

public class Main {
    public static void main(String[] args) {
        new DataFactory().createData();
        MenuService menuService = new MenuService();
        menuService.menu();
    }
}