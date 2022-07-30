package org.oobootcamp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduateParkingBoyTest {
    //### AC 1：当停车场有空闲车位时，申请停车，停车成功，取到停车票
//- Example 1
//            -  Given 共一个停车场共30个车位，已停车辆为0， When 申请停车A时, Then 停车成功，空闲车位为29，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}
//- Example 2
//            -  Given 共两个停车场各2个车位，停车场1车位已满，停车场2已停车辆为1， When 申请停车A时, Then 停车成功，停车场2空闲车位变更为0，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:2}}
//- Example 3
//            -  Given 共两个停车场各2个车位，停车场1已停车辆为0，停车场2已停车辆为1， When 申请停车A时, Then 停车成功，停车场1空闲车位变更为1，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}
    @Test
    void should_parking_succeed_when_parking_given_1_parking_lot_freespace_is_30() {
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(1, 30));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Result result = graduateParkingBoy.parking("A");

        assertThat(result.getCode()).isEqualTo("停车成功");
        assertThat(result.getTicket().getCarNum()).isEqualTo("A");
        assertThat(result.getTicket().getParkNum()).isEqualTo(1);
        assertThat(parkingLots.get(0).getFreeSpace()).isEqualTo(29);
    }
}
