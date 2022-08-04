package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private final Integer capacity;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFreeSpace() {
        return capacity - ParkedCars.size();
    }

    private HashMap<Ticket,Car> ParkedCars = new HashMap<>();

    public Ticket parking(Car car) throws ParkingLotFullException {
        if (getFreeSpace() > 0) {
            Ticket ticket=new Ticket();
            ParkedCars.put(ticket,car);
            return ticket;
        }

        throw new ParkingLotFullException();
    }


    public Car picking(Ticket ticket) throws InvalidTicketException {
        if (isTicketValid(ticket)) {
            Car car= ParkedCars.get(ticket);
            ParkedCars.remove(ticket);
            return car;
        }
       throw  new InvalidTicketException();
    }

    public Boolean isTicketValid(Ticket ticket) {
        return ParkedCars.containsKey(ticket);
    }
}
