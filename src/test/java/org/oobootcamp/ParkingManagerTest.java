package org.oobootcamp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingManagerTest {
    @Test
    void should_parking_into_manager_lot_when_parking_given_manager_lot_has_free_space() throws Exception {
        ParkingBoy parkingBoy2 = TestHelper.BuildGraduateParkingBoy(new ParkingLot(1));
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(
                new ArrayList<>() {{
                    add(parkingBoy2);
                }},
                new ArrayList<>() {{
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
                new ArrayList<>() {{
                    add(parkingBoy2);
                }},
                new ArrayList<>() {{
                    add(parkingLot);
                }});
        Car car = new Car();

        Ticket ticket = parkingManager.manageParking(car);

        assertThat(parkingBoy2.picking(ticket)).isEqualTo(car);
    }
}
