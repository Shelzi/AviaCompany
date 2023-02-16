package com.solvd.AviaCompany.utils;

import com.solvd.AviaCompany.hierarchy.Flight;
import com.solvd.AviaCompany.utils.menu.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        menuService.menu();
    }
}