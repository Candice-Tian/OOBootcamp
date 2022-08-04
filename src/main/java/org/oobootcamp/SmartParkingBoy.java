package org.oobootcamp;

import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    public Ticket parking(Car car) throws ParkingLotFullException {
        ParkingLots.sort(getParkingLotComparator());
        ParkingLot parkingLot = ParkingLots.get(0);
        if (parkingLot.getFreeSpace() > 0) {
            Ticket ticket = parkingLot.parking(car);
            TicketParkinglotMap.put(ticket, parkingLot);
            return ticket;
        }
        throw new ParkingLotFullException();
    }

    private static Comparator<ParkingLot> getParkingLotComparator() {
        return (o1, o2) -> {
            int diff = o1.getFreeSpace() - o2.getFreeSpace();
            return diff == 0 ? 0 : -diff;
        };
    }
}
