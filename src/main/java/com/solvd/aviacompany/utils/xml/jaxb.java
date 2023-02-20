package com.solvd.aviacompany.utils.xml;

import com.solvd.aviacompany.hierarchy.*;
import com.solvd.aviacompany.service.impl.IntIntPair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class jaxb {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws JAXBException, IOException {
        Country belarus = new Country(1, "Belarus");
        List<City> cities = List.of(new City(1, "Minsk", belarus), new City(2, "Vitebsk", belarus),
                new City(3, "Gomel", belarus));
        List<IntIntPair> weights = List.of(new IntIntPair(100, 1000), new IntIntPair(200, 1200),
                new IntIntPair(300, 1300));
        ComplexRoute complexRoute = new ComplexRoute(cities, weights);
        marshalComplexRoute(complexRoute, "ComplexRoute.xml");

        List<Ticket> tickets = complexRoute.getFlights(new Passenger(1,"Vlad", "Zzhuravlev"));

    }

    public static void marshalComplexRoute(ComplexRoute complexRoute, String filename) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(complexRoute.getClass());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(complexRoute, new File(filename));
    }

    public static ComplexRoute unmarshallComplexRoute(String filename) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ComplexRoute.class);
        return (ComplexRoute) context.createUnmarshaller()
                .unmarshal(new FileReader(filename));
    }
}
