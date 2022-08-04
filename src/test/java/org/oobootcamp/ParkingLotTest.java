package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_parking_succeed_when_parking_given_has_free_space() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(30);
        Ticket ticket = parkingLot.parking(new Car());

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_parking_failed_when_parking_given_has_not_free_space() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parking(new Car());
        parkingLot.parking(new Car());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.parking(new Car()));

    }

    @Test
    void should_picking_succeed_when_picking_given_ticket_is_valid() throws Exception {
        ParkingLot parkingLot = new ParkingLot(30);
        Car car=new Car();
        Ticket ticket = parkingLot.parking(car);

        assertThat( parkingLot.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {
        ParkingLot parkingLot = new ParkingLot(30);
        Car car=new Car();
        parkingLot.parking(car);

        Ticket ticket =new Ticket();

        assertThrows(InvalidTicketException.class, () -> parkingLot.picking(ticket));
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {
        ParkingLot parkingLot = new ParkingLot(30);
        Car car=new Car();
        Ticket ticket = parkingLot.parking(car);
        parkingLot.picking(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingLot.picking(ticket));
    }

    @Test
    void should_free_space_reduce_when_parking_car() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(30);
        Integer originalSpace=parkingLot.getFreeSpace();
        parkingLot.parking(new Car());

        Integer space=parkingLot.getFreeSpace();
        assertThat(space).isEqualTo(originalSpace-1);
    }
    @Test
    void should_free_space_increase_when_picking_car() throws Exception {
        ParkingLot parkingLot = new ParkingLot(30);
        Ticket ticket = parkingLot.parking(new Car());
        Integer originalSpace=parkingLot.getFreeSpace();

        parkingLot.picking(ticket);
        Integer space=parkingLot.getFreeSpace();
        assertThat(space).isEqualTo(originalSpace+1);
    }
}
