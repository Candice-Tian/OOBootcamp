package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ParkingBoy {
    protected ArrayList<ParkingLot> ParkingLots;
    protected HashMap<Ticket, ParkingLot> ticketParkingLotMap = new HashMap<>();

    public Car picking(Ticket ticket) throws InvalidTicketException {
        ParkingLot parkingLot = ticketParkingLotMap.get(ticket);
        if (parkingLot != null) {
            return parkingLot.picking(ticket);
        }
        throw new InvalidTicketException();
    }

    public Ticket parking(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot = getAvailableParkingLot();

        if (parkingLot != null) {
            Ticket ticket = parkingLot.parking(car);
            ticketParkingLotMap.put(ticket, parkingLot);
            return ticket;
        }
        throw new ParkingLotFullException();
    }

    public abstract ParkingLot getAvailableParkingLot() throws ParkingLotFullException;
}
