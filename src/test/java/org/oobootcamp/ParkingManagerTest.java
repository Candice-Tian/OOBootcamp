package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {
    @Test
    void should_parking_into_manager_lot_when_parking_given_manager_lot_has_free_space() throws Exception {
        ParkingBoy parkingBoy2 = TestHelper.BuildGraduateParkingBoy(new ParkingLot(1));
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(parkingBoy2);
                }},
                new ArrayList<ParkingLot>() {{
                    add(parkingLot);
                }});
        Car car = new Car();

        Ticket ticket = parkingManager.manageParking(car);

        assertThat(parkingLot.picking(ticket)).isEqualTo(car);
    }
    //  - Given 经理自己管理的停车场已停满，2号小弟管理的停车场没停满，When 停车时 , Then 停车成功，停入2号小弟管理的停车场，返回Ticket

    @Test
    void should_parking_into_boy2_lot_when_parking_given_manager_lot_has_no_free_space_and_boy2_lot_has_free_space() throws Exception {
        ParkingBoy parkingBoy2 = TestHelper.BuildGraduateParkingBoy(new ParkingLot(1));
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.parking(new Car());
        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(parkingBoy2);
                }},
                new ArrayList<ParkingLot>() {{
                    add(parkingLot);
                }});
        Car car = new Car();

        Ticket ticket = parkingManager.manageParking(car);

        assertThat(parkingBoy2.picking(ticket)).isEqualTo(car);
    }

    //Given 1、2号小弟管理的停车场都已停满，When 停车时，Then停车失败，返回“车位已满，停车失败”
    @Test
    void should_parking_failed_when_parking_given_all_parking_boy_has_no_free_space() throws Exception {
        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(TestHelper.BuildGraduateParkingBoy(new ParkingLot(1)));
                }},
                new ArrayList<ParkingLot>() {{
                    add(new ParkingLot(1));
                }});
        parkingManager.manageParking(new Car());
        parkingManager.manageParking(new Car());


        assertThrows(ParkingLotFullException.class,
                ()-> parkingManager.manageParking(new Car()));

    }

    //Given Ticket有效（车辆在停车场时） When 取车时，Then取车成功，取出的车与指定取的车一致
    @Test
    void should_picking_succeed_when_picking_given_ticket_is_valid() throws Exception {

        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(TestHelper.BuildGraduateParkingBoy(new ParkingLot(1)));
                }},
                new ArrayList<ParkingLot>() {{
                    add(new ParkingLot(1));
                }});

        Car car = new Car();
        Ticket ticket = parkingManager.manageParking(car);

        assertThat(parkingManager.managePicking(ticket)).isEqualTo(car);
    }

    // Given Ticket属于别的停车场，When使用该Ticket取车时, Then 取车失败，返回 Ticket无效
    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {

        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(TestHelper.BuildGraduateParkingBoy(new ParkingLot(1)));
                }},
                new ArrayList<ParkingLot>() {{
                    add(new ParkingLot(1));
                }});

        parkingManager.manageParking(new Car());

        assertThrows(InvalidTicketException.class,
                ()-> parkingManager.managePicking(new Ticket()));
    }

    //Given 使用Ticket取走一辆车，When再次使用该Ticket申请取车时, Then 取车失败，返回 Ticket无效

    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {

        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<ParkingBoy>() {{
                    add(TestHelper.BuildGraduateParkingBoy(new ParkingLot(1)));
                }},
                new ArrayList<ParkingLot>() {{
                    add(new ParkingLot(1));
                }});

        Ticket ticket = parkingManager.manageParking(new Car());
        parkingManager.managePicking(ticket);

        assertThrows(InvalidTicketException.class,
                ()-> parkingManager.managePicking(ticket));
    }
}
