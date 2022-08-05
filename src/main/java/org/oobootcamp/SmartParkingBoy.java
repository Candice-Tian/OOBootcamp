package org.oobootcamp;

import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    public Ticket parking(Car car) throws ParkingLotFullException {
        ParkingLots.sort(getParkingLotComparator);
        ParkingLot parkingLot = ParkingLots.get(0);
        if (parkingLot.getFreeSpace() > 0) {
            Ticket ticket = parkingLot.parking(car);
            TicketParkinglotMap.put(ticket, parkingLot);
            return ticket;
        }
        throw new ParkingLotFullException();
    }

    private static final Comparator<ParkingLot> getParkingLotComparator =
            (o1, o2) -> o2.getFreeSpace() - o1.getFreeSpace();
}
