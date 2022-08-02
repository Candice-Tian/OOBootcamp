package org.oobootcamp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduateParkingBoyTest {
    //### AC 1：当停车场有空闲车位时，申请停车，停车成功，取到停车票
//- Example 1
//            -  Given 共一个停车场共30个车位，已停车辆为0， When 申请停车A时, Then 停车成功，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}
    @Test
    void should_parking_succeed_when_parking_given_1_parking_lot_free_space_is_30() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, 30));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Result result = graduateParkingBoy.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket() != null);
    }

    //- Example 2
//            -  Given 共两个停车场各2个车位，停车场1车位已满，停车场2已停车辆为0， When 申请停车A时, Then 停车成功，停车场2空闲车位变更为0，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:2}}
    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_only_lot2_has_free_space_2() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1, 2);
        parkingLot1.parking("B");
        parkingLot1.parking("C");
        parkingLots.add(parkingLot1);
        parkingLots.add(new ParkingLot(2, 2));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Result result = graduateParkingBoy.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket() != null);
    }


    //- Example 3
//            -  Given 共两个停车场各2个车位，停车场1已停车辆为0，停车场2已停车辆为1， When 申请停车A时, Then 停车成功，停车场1空闲车位变更为1，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}
    @Test
    void should_parking_succeed_when_parking_given_2_parking_lots_both_lots_have_free_space() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, 2));
        ParkingLot parkingLot2 = new ParkingLot(2, 2);
        parkingLot2.parking("B");
        parkingLots.add(parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Result result = graduateParkingBoy.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket() != null);
    }

//    Given 共两个停车场各2个车位，停车场1、2车位均已满，When 申请停车时, Then 停车失败，返回{"code":"停车失败，车位已满", "ticket":null}

    @Test
    void should_parking_failed_when_parking_given_2_parking_lots_both_lots_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1, 2);
        parkingLot1.parking("B");
        parkingLot1.parking("C");
        parkingLots.add(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot(2, 2);
        parkingLot2.parking("D");
        parkingLot2.parking("E");
        parkingLots.add(parkingLot2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Result result = graduateParkingBoy.parking("A");

        assertThat(result.getCode()).isEqualTo("停车失败，车位已满");
    }

    //Given 停车票{carNum:"A", "parkNum":1}有效, When 使用该停车票申请取车A时, Then 取车成功，停车场1的空闲车位+1
    @Test
    void should_unparking_succeed_when_unparking_given_ticket_valid() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, 2));
        parkingLots.add(new ParkingLot(2, 2));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.parking("A").getTicket();
        graduateParkingBoy.parking("B");

        Result result = graduateParkingBoy.unparking(ticket);

        assertThat(result.getCode()).isEqualTo("取车成功");
        assertThat(result.getTicket() != null);
    }

    //Given 停车票{carNum:"A", "parkNum":1}，但是车辆A不在停车场1， When使用该停车票申请取车A, Then 取车失败，返回{"code":"取车失败, 停车票无效", "ticket":null}
    @Test
    void should_unparking_failed_when_unparking_given_ticekt_invalid() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, 2));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.parking("A").getTicket();
        graduateParkingBoy.unparking(ticket);

        Result result = graduateParkingBoy.unparking(ticket);

        assertThat(result.getCode()).isEqualTo("取车失败, 停车票无效");
    }
}
