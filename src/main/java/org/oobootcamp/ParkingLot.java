package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private Integer ParkingLotNumber;
    private Integer capacity;

    public ParkingLot(Integer parkingLotNumber, Integer capacity) {
        ParkingLotNumber = parkingLotNumber;
        this.capacity = capacity;
    }

    public Integer getParkingLotNumber() {
        return ParkingLotNumber;
    }

    public Integer getFreeSpace() {
        return capacity - ParkedCars.size();
    }

    private HashMap<Ticket,Car> ParkedCars = new HashMap<Ticket,Car>();

    public Ticket parking(Car car) throws ParkingLotFullException {
        if (getFreeSpace() > 0) {
            Ticket ticket=new Ticket();
            ParkedCars.put(ticket,car);
            return ticket;
        }

        throw new ParkingLotFullException();
    }


    public Car picking(Ticket ticket) throws InvalidTicketException {
        Result result = new Result();
        if (isTicketValid(ticket)) {
            Car car= ParkedCars.get(ticket);
            ParkedCars.remove(ticket);
            return car;
        }
       throw  new InvalidTicketException();
    }

    private Boolean isTicketValid(Ticket ticket) {
        return ParkedCars.containsKey(ticket);
    }
}
