package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ParkingBoy {
    protected ArrayList<ParkingLot> ParkingLots;
    protected HashMap<Ticket, ParkingLot> TicketParkinglotMap = new HashMap<>();

    public Car picking(Ticket ticket) throws InvalidTicketException {
        ParkingLot parkingLot = getParkingLotByTicket(ticket);
        if (parkingLot != null) {
            return parkingLot.picking(ticket);
        }
        throw new InvalidTicketException();
    }

    private ParkingLot getParkingLotByTicket(Ticket ticket) {
        return TicketParkinglotMap.get(ticket);
    }

    public abstract Ticket parking(Car car) throws ParkingLotFullException;
}
