package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {

//    Example
//    Given 停车场共30个车位，已停车辆为0， When 申请停车时, Then 停车成功，空闲车位为29，返回{"code":"停车成功",ticket:{"carNum":"A"}}

    @Test
    void should_parking_succeed_when_parking_given_has_free_space() {
        ParkingLot parkingLot = new ParkingLot(30);
        Result result=parkingLot.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket().getCarNum()).isEqualTo("A");
        assertThat(parkingLot.getFreeSpace()).isEqualTo(29);
    }

//    @Test
//    void should_parking_failed_when_parking_given_has_not_free_space() {
//        ParkingLot parkingLot = new ParkingLot(2);
//        parkingLot.parking("A");
//        parkingLot.parking("B");
//
//        assertThat(parkingLot.parking("C")).isEqualTo("failed");
//    }
//
//    @Test
//    void should_picking_succeed_when_picking_given_car_in_parking_lot() {
//        ParkingLot parkingLot = new ParkingLot(30);
//        parkingLot.parking("A");
//        int freeSpace=parkingLot.getFreeSpace();
//
//        assertThat(parkingLot.picking("A")).isEqualTo("succeed");
//        assertThat(parkingLot.getFreeSpace()).isEqualTo(freeSpace+1);
//    }
//
//    @Test
//    void should_picking_failed_when_picking_given_car_not_in_parking_lot() {
//        ParkingLot parkingLot = new ParkingLot(30);
//        parkingLot.parking("C");
//        int freeSpace=parkingLot.getFreeSpace();
//
//        assertThat(parkingLot.picking("A")).isEqualTo("failed");
//        assertThat(parkingLot.getFreeSpace()).isEqualTo(freeSpace);
//
//    }
}
