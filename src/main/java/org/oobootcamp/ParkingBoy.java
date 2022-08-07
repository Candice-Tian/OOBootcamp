package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ParkingBoy {
    protected ArrayList<ParkingLot> ParkingLots;

    public Car picking(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkingLot : ParkingLots) {
            if (parkingLot.isTicketValid(ticket)) {
                return parkingLot.picking(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    public Ticket parking(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot = getAvailableParkingLot();

        if (parkingLot != null) {
            Ticket ticket = parkingLot.parking(car);
            return ticket;
        }
        throw new ParkingLotFullException();
    }

    public abstract ParkingLot getAvailableParkingLot() throws ParkingLotFullException;

}
