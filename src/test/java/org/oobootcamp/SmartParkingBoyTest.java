package org.oobootcamp;

import org.junit.jupiter.api.Test;
import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    //测试数据是否有点重复了
    @Test
    void should_parking_into_lot1_when_parking_given_3_parking_lot_and_free_space_are_equal() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(parkingLot1.picking(ticket)).isEqualTo(car);
    }


    @Test
    void should_parking_into_lot2_when_parking_given_3_parking_lot_and_lot2_has_more_free_space() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot2);
        ParkingLot parkingLot3 = new ParkingLot(2);
        parkingLots.add(parkingLot3);
        parkingLot3.parking(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.parking(new Car());

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(parkingLot2.picking(ticket)).isEqualTo(car);
    }

    //    -  Given 有停车场1,2,3，总停车位都是2， 停车场1,3没有停车，停车场2停了一辆车,When 停车B时，Then停车成功，停入停车场1，返回Ticket
    @Test
    void should_parking_into_lot1_when_parking_given_3_parking_lot_and_lot1_lot3_has_more_free_space() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.parking(new Car());
        parkingLots.add(parkingLot2);
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(parkingLot1.picking(ticket)).isEqualTo(car);
    }

    //    Given 有两个停车场1,2，总停车位都是1，停车场1,2均已停1辆车，When 停车C时，Then停车失败，返回车位已满，停车失败
    @Test
    void should_parking_failed_when_parking_given_2_parking_lot_and_both_have_no_free_space() throws ParkingLotFullException {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.parking(new Car());
        smartParkingBoy.parking(new Car());

        assertThrows(ParkingLotFullException.class, () -> smartParkingBoy.parking(new Car()));
    }

    //Given Ticket有效（车辆A在停车场时） When 取车A时，Then取车成功
    @Test
    void should_picking_succeed_when_picking_given_ticket_valid() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        Ticket ticket = smartParkingBoy.parking(car);

        assertThat(smartParkingBoy.picking(ticket)).isEqualTo(car);
    }

    //Given 共1个停车场，有2个车位，停车场已停车辆["C"]，When使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
    @Test
    void should_picking_failed_when_picking_given_ticket_is_invalid() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.parking(new Car());

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.picking(new Ticket()));
    }

    //Given 共1个停车场，有2个车位，停车场已停车辆["A"]，使用停车票{carNum:"A"} 取走A车，When再次使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
    @Test
    void should_picking_failed_when_picking_given_ticket_is_used() throws Exception {
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.parking(new Car());
        smartParkingBoy.picking(ticket);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.picking(ticket));
    }
}
