package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {

//    Example
//    Given 停车场共30个车位，已停车辆为0， When 申请停车时, Then 停车成功，空闲车位为29，返回{"code":"停车成功",ticket:{"carNum":"A"}}

    @Test
    void should_parking_succeed_when_parking_given_has_free_space() {
        ParkingLot parkingLot = new ParkingLot(30);
        Result result = parkingLot.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket().getCarNum()).isEqualTo("A");
        assertThat(parkingLot.getFreeSpace()).isEqualTo(29);
    }

    //    Example
//    Given 停车场共2个停车位，已经停了2个车辆，空闲车位0个, When 申请停车时, Then 停车失败, 返回{"code":"停车失败, 停车位已满", "data":null}
    @Test
    void should_parking_failed_when_parking_given_has_not_free_space() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parking("A");
        parkingLot.parking("B");

        Result result = parkingLot.parking("C");
        assertThat(result.getCode()).isEqualTo("停车失败, 停车位已满");
    }

    //     Example
//     Given 停车场已停车辆["A","B"], When 使用停车票{carNum:"A"} 申请取车A时, Then 取车成功，已停车辆变更为["B"]
    @Test
    void should_unparking_succeed_when_unparking_given_ticket_is_valid() {
        ParkingLot parkingLot = new ParkingLot(30);
        parkingLot.parking("A");
        parkingLot.parking("B");

        Ticket ticket = new Ticket("A");
        var result = parkingLot.unparking(ticket);
        assertThat(result.getCode()).isEqualTo("取车成功");
    }
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
