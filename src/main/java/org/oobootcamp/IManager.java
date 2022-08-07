package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

public interface IManager {
    Ticket manageParking(Car car) throws ParkingLotFullException;

    Car managePicking(Ticket ticket) throws InvalidTicketException;
}
