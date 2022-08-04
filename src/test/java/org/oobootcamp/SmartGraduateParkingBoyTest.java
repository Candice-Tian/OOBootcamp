//package org.oobootcamp;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class SmartGraduateParkingBoyTest {
////    -  Given 有停车场1，2,3，总停车位都是2，都已停0辆车, When 停车A时, Then 停车成功，停入停车场1，返回Ticket
//    @Test
//    void should_parking_into_lot1_when_parking_given_3_parking_lot_and_free_space_are_equal() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 2));
//        parkingLots.add(new ParkingLot(2, 2));
//        parkingLots.add(new ParkingLot(3, 2));
//        SmartGraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        Result result = smartGraduateParkingBoy.parking("A");
//
//        assertThat(result.getCode()).isEqualTo("停车成功");
//        assertThat(result.getTicket()).isNotNull();
//        assertThat(parkingLots.get(0).getFreeSpace()).isEqualTo(1);
//    }
//
////    -  Given 有停车场1,2，3，总停车位都是2，停车场1,3已停一辆车, 停车场2没有停车, When 停车C时 , Then 停车成功，停入停车场2，返回Ticket
//
//    @Test
//    void should_parking_into_lot2_when_parking_given_3_parking_lot_and_lot2_has_more_free_space() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 2));
//        ParkingLot parkingLot2 = new ParkingLot(2, 2);
//        parkingLots.add(parkingLot2);
//        ParkingLot parkingLot3 = new ParkingLot(3, 2);
//        parkingLots.add(parkingLot3);
//        parkingLot3.parking("B");
//        SmartGraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        smartGraduateParkingBoy.parking("A");
//        Result result = smartGraduateParkingBoy.parking("C");
//
//        assertThat(result.getCode()).isEqualTo("停车成功");
//        assertThat(result.getTicket()).isNotNull();
//        assertThat(parkingLot2.getFreeSpace()).isEqualTo(1);
//    }
//
//    //    -  Given 有停车场1,2,3，总停车位都是2， 停车场1,3没有停车，停车场2停了一辆车,When 停车B时，Then停车成功，停入停车场1，返回Ticket
//    @Test
//    void should_parking_into_lot1_when_parking_given_3_parking_lot_and_lot1_lot3_has_more_free_space() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        ParkingLot parkingLot1=new ParkingLot(1, 2);
//        parkingLots.add(parkingLot1);
//        ParkingLot parkingLot2 = new ParkingLot(2, 2);
//        parkingLot2.parking("A");
//        parkingLots.add(parkingLot2);
//        parkingLots.add(new ParkingLot(3, 2));
//        SmartGraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//
//        Result result = smartGraduateParkingBoy.parking("B");
//
//        assertThat(result.getCode()).isEqualTo("停车成功");
//        assertThat(result.getTicket()).isNotNull();
//        assertThat(parkingLot1.getFreeSpace()).isEqualTo(1);
//    }
//
//    //    Given 有两个停车场1,2，总停车位都是1，停车场1,2均已停1辆车，When 停车C时，Then停车失败，返回车位已满，停车失败
//    @Test
//    void should_parking_failed_when_parking_given_2_parking_lot_and_both_have_no_free_space() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 1));
//        parkingLots.add(new ParkingLot(2, 1));
//        SmartGraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        smartGraduateParkingBoy.parking("A");
//        smartGraduateParkingBoy.parking("B");
//        Result result = smartGraduateParkingBoy.parking("C");
//
//        assertThat(result.getCode()).isEqualTo("停车失败，车位已满");
//    }
//
//    //Given Ticket有效（车辆A在停车场时） When 取车A时，Then取车成功
//    @Test
//    void should_unparking_succeed_when_unparking_given_ticket_valid() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 2));
//        parkingLots.add(new ParkingLot(2, 2));
//        SmartGraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        Ticket ticket = smartGraduateParkingBoy.parking("A").getTicket();
//
//        Result result = smartGraduateParkingBoy.unparking(ticket);
//
//        assertThat(result.getCode()).isEqualTo("取车成功");
//    }
//
//    //Given 共1个停车场，有2个车位，停车场已停车辆["C"]，When使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
//    @Test
//    void should_unparking_failed_when_unparking_given_ticekt_is_invalid() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 2));
//        GraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        smartGraduateParkingBoy.parking("C");
//
//        Ticket ticket =new Ticket("A");
//        Result result = smartGraduateParkingBoy.unparking(ticket);
//        assertThat(result.getCode()).isEqualTo("取车失败, 停车票无效");
//    }
//
//    //Given 共1个停车场，有2个车位，停车场已停车辆["A"]，使用停车票{carNum:"A"} 取走A车，When再次使用停车票{carNum:"A"} 申请取车A时, Then 取车失败，返回{"code":"取车失败, 停车票无效"}
//    @Test
//    void should_unparking_failed_when_unparking_given_ticekt_is_used() {
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(new ParkingLot(1, 2));
//        GraduateParkingBoy smartGraduateParkingBoy = new SmartGraduateParkingBoy(parkingLots);
//        Ticket ticket = smartGraduateParkingBoy.parking("A").getTicket();
//        smartGraduateParkingBoy.unparking(ticket);
//
//        Result result = smartGraduateParkingBoy.unparking(ticket);
//        assertThat(result.getCode()).isEqualTo("取车失败, 停车票无效");
//    }
//
//
//}
