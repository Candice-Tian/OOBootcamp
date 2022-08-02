package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {

//    Given 停车场共30个车位，已停车辆为0， When 申请停车时, Then 停车成功，返回ticket
    @Test
    void should_parking_succeed_when_parking_given_has_free_space() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        Result result = parkingLot.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket()).isNotNull();
    }

//    Given 停车场共2个停车位，已经停了2个车辆，空闲车位0个, When 申请停车时, Then 停车失败, 返回{"code":"停车失败, 停车位已满", "ticket":null}
    @Test
    void should_parking_failed_when_parking_given_has_not_free_space() {
        ParkingLot parkingLot = new ParkingLot(1, 2);
        parkingLot.parking("A");
        parkingLot.parking("B");

        Result result = parkingLot.parking("C");
        assertThat(result.getCode()).isEqualTo("停车失败, 停车位已满");
    }

//     Given 停车场已停车辆["A"], When 使用停车票{carNum:"A"} 申请取车A时, Then 取车成功
    @Test
    void should_unparking_succeed_when_unparking_given_ticket_is_valid() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        Ticket ticket = parkingLot.parking("A").getTicket();

        Result result = parkingLot.unparking(ticket);
        assertThat(result.getCode()).isEqualTo("取车成功");
    }

//    Example
//    Given 停车场已停车辆["C"], When 使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
    @Test
    void should_unparking_failed_when_unparking_given_ticket_is_invalid() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        parkingLot.parking("C");

        Ticket ticket = new Ticket("A");
        Result result = parkingLot.unparking(ticket);
        assertThat(result.getCode()).isEqualTo("取车失败, 停车票无效");
    }

    // Given 停车场已停车辆["A"] 使用停车票{carNum:"A"}取走A车，When再次使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
    @Test
    void should_unparking_failed_when_unparking_given_ticket_is_used() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        Ticket ticket = parkingLot.parking("A").getTicket();
        parkingLot.unparking(ticket);

        Result result = parkingLot.unparking(ticket);
        assertThat(result.getCode()).isEqualTo("取车失败, 停车票无效");
    }

    @Test
    void should_free_space_reduce_when_parking_car() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        Integer originalSpace=parkingLot.getFreeSpace();
        parkingLot.parking("A");

        Integer space=parkingLot.getFreeSpace();
        assertThat(space).isEqualTo(originalSpace-1);
    }
    @Test
    void should_free_space_increase_when_unparking_car() {
        ParkingLot parkingLot = new ParkingLot(1, 30);
        Ticket ticket = parkingLot.parking("A").getTicket();
        Integer originalSpace=parkingLot.getFreeSpace();

        parkingLot.unparking(ticket);
        Integer space=parkingLot.getFreeSpace();
        assertThat(space).isEqualTo(originalSpace+1);
    }
}
