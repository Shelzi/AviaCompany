package com.solvd.aviacompany.utils.xml;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.solvd.aviacompany.hierarchy.Ticket;
import com.solvd.aviacompany.service.impl.IntIntPair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "Tickets")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("tickets")
public class XmlTicket{
    @XmlElementWrapper(name = "tickets")
    @XmlElement(name = "ticket", type = Ticket.class)
    @JacksonXmlElementWrapper(localName = "tickets")
    List<Ticket> tickets;

    @XmlElement
    IntIntPair weight;

}