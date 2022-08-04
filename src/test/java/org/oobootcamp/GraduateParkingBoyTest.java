package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {
    @Test
    void should_parking_succeed_when_parking_given_1_parking_lot_free_space_is_30() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLots.add(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_only_lot2_has_free_space_2() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.parking(new Car());
        parkingLots.add(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot2.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_both_lots_have_free_space() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(new ParkingLot(1));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(ticket).isNotNull();
        assertThat(parkingLot1.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_parking_failed_when_parking_given_2_parking_lots_both_lots_full() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.parking(new Car());
        parkingLots.add(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.parking(new Car());
        parkingLots.add(parkingLot2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);

        assertThrows(ParkingLotFullException.class, () -> graduateParkingBoy.parking(new Car()));
    }

    @Test
    void should_picking_succeed_when_picking_given_ticket_valid() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLots.add(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        assertThat(graduateParkingBoy.picking(ticket)).isEqualTo(car);
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLots.add(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();
        graduateParkingBoy.parking(car);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.picking(new Ticket()));
    }

    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLots.add(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.parking(car);

        graduateParkingBoy.picking(ticket);
        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.picking(ticket));
    }
}
