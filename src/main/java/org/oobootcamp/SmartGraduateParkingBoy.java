//package org.oobootcamp;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public class SmartGraduateParkingBoy extends GraduateParkingBoy {
//    public SmartGraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
//        super(parkingLots);
//    }
//
//    public Result parking(String carNumber) {
//        Result result = new Result();
//
//        Collections.sort(ParkingLots, getParkingLotComparator());
//        ParkingLot parkingLot = ParkingLots.get(0);
//        if (parkingLot.getFreeSpace() > 0) {
//            result = parkingLot.parking(carNumber);
//            ParkinglotCarMap.put(carNumber, parkingLot.getParkingLotNumber());
//            return result;
//        }
//
//        result.setCode("停车失败，车位已满");
//        return result;
//    }
//
//    private static Comparator<ParkingLot> getParkingLotComparator() {
//        return (o1, o2) -> {
//            Integer diff = o1.getFreeSpace() - o2.getFreeSpace();
//            return diff == 0 ? 0 : -diff;
//        };
//    }
//}
