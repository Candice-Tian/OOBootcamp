//package org.oobootcamp;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class GraduateParkingBoy {
//    protected final ArrayList<ParkingLot> ParkingLots;
//    protected HashMap<String,Integer> ParkinglotCarMap = new HashMap<>();
//
//    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
//        ParkingLots = parkingLots;
//    }
//
//    public Result parking(String carNumber) {
//        Result result = new Result();
//        for (ParkingLot parkingLot : ParkingLots) {
//            if (parkingLot.getFreeSpace() > 0) {
//                result = parkingLot.parking(carNumber);
//                ParkinglotCarMap.put(carNumber,parkingLot.getParkingLotNumber());
//                return result;
//            }
//        }
//        result.setCode("停车失败，车位已满");
//        return result;
//    }
//
//    public Result unparking(Ticket ticket) {
//        ParkingLot lot = getParkingLotByCarNum(ticket.getCarNum());
//        if (lot != null) {
//            return lot.picking(ticket);
//        }
//        Result result = new Result();
//        result.setCode("取车失败, 停车票无效");
//        return result;
//    }
//
//    private ParkingLot getParkingLotByCarNum(String carNum) {
//        Integer parkNum = this.ParkinglotCarMap.get(carNum);
//        for (ParkingLot parkingLot : ParkingLots) {
//            if (parkingLot.getParkingLotNumber().equals(parkNum)) {
//                return parkingLot;
//            }
//        }
//        return null;
//    }
//}
