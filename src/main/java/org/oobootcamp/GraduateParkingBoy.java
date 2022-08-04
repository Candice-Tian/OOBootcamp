package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.HashMap;

public class GraduateParkingBoy {
    protected final ArrayList<ParkingLot> ParkingLots;
    protected HashMap<Ticket, ParkingLot> TicketParkinglotMap = new HashMap<>();

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    public Ticket parking(Car car) throws ParkingLotFullException {
        for (ParkingLot parkingLot : ParkingLots) {
            if (parkingLot.getFreeSpace() > 0) {
                Ticket ticket = parkingLot.parking(car);
                TicketParkinglotMap.put(ticket, parkingLot);
                return ticket;
            }
        }
        throw new ParkingLotFullException();
    }

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
}
