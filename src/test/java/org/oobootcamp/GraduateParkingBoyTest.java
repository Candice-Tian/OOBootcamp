package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {
    @Test
    void should_parking_succeed_when_parking_given_1_parking_lot_free_space_is_30() throws Exception {
        ParkingLot parkingLot = new ParkingLot(30);
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(parkingLot);

        Car car = new Car();

        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_only_lot2_has_free_space_2() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.parking(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(parkingLot1, parkingLot2);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot2.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_both_lots_have_free_space() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(parkingLot1, new ParkingLot(1));
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot1.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_failed_when_parking_given_2_parking_lots_both_lots_full() throws Exception {
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(new ParkingLot(1), new ParkingLot(1));
        graduateParkingBoy.parking(new Car());
        graduateParkingBoy.parking(new Car());

        assertThrows(ParkingLotFullException.class, () -> graduateParkingBoy.parking(new Car()));
    }

    @Test
    void should_picking_succeed_when_picking_given_ticket_valid() throws Exception {
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(new ParkingLot(30), new ParkingLot(30));
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(graduateParkingBoy.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(new ParkingLot(30), new ParkingLot(30));
        Car car = new Car();
        graduateParkingBoy.parking(car);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.picking(new Ticket()));
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {
        GraduateParkingBoy graduateParkingBoy = TestHelper.BuildGraduateParkingBoy(new ParkingLot(30), new ParkingLot(30));
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        graduateParkingBoy.picking(ticket);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.picking(ticket));
    }
}
