package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    @Test
    void should_parking_into_lot2_when_parking_given_3_parking_lot_and_lot2_has_more_free_space() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLot1.parking(new Car());
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingLot parkingLot3 = new ParkingLot(2);
        parkingLot3.parking(new Car());
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(parkingLot2.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_into_lot1_when_parking_given_3_parking_lot_and_lot1_lot3_has_more_free_space() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.parking(new Car());
        ParkingLot parkingLot3 = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(parkingLot1, parkingLot2, parkingLot3);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(parkingLot1.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_failed_when_parking_given_2_parking_lot_and_both_have_no_free_space() throws ParkingLotFullException {
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(new ParkingLot(1), new ParkingLot(1));

        smartParkingBoy.parking(new Car());
        smartParkingBoy.parking(new Car());

        assertThrows(ParkingLotFullException.class, () -> smartParkingBoy.parking(new Car()));
    }

    @Test
    void should_picking_succeed_when_picking_given_ticket_is_valid() throws Exception {
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(new ParkingLot(2));

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(smartParkingBoy.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(new ParkingLot(2));
        smartParkingBoy.parking(new Car());

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.picking(new Ticket()));
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {
        SmartParkingBoy smartParkingBoy = BuildSmartParkingBoy(new ParkingLot(2));

        Ticket ticket = smartParkingBoy.parking(new Car());
        smartParkingBoy.picking(ticket);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.picking(ticket));
    }

    private SmartParkingBoy BuildSmartParkingBoy(ParkingLot... parkingLots) {
        return new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLots)));
    }
}
