package org.oobootcamp;

import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

public class GraduateParkingBoy extends ParkingBoy {


    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.ParkingLots = parkingLots;
    }

    @Override
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
}
