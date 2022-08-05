package org.oobootcamp;

import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

public class GraduateParkingBoy extends ParkingBoy {


    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.ParkingLots = parkingLots;
    }

    @Override
    public Ticket parking(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot= ParkingLots.stream().filter(p->p.getFreeSpace()>0).findFirst().orElse(null);
        if(parkingLot==null){
            throw new ParkingLotFullException();
        }
        Ticket ticket = parkingLot.parking(car);
        TicketParkinglotMap.put(ticket, parkingLot);
        return ticket;
    }
}
