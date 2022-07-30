package org.oobootcamp;

import java.util.ArrayList;

public class GraduateParkingBoy {
    private final ArrayList<ParkingLot> ParkingLots;

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    public Result parking(String carNumber) {
        Result result = new Result();
        for (ParkingLot parkingLot : ParkingLots) {
            if (parkingLot.getFreeSpace() > 0) {
                result = parkingLot.parking(carNumber);
                result.getTicket().setParkNum(parkingLot.getParkingLotNumber());
                return result;
            }
        }
        result.setCode("停车失败，车位已满");
        return result;
    }

    public Result unparking(Ticket ticket) {
        ParkingLot lot = GetParkingLotByNum(ticket.getParkNum());
        if (lot != null) {
            return lot.unparking(ticket);
        }
        Result result = new Result();
        result.setCode("取车失败, 停车票无效");
        return result;
    }

    private ParkingLot GetParkingLotByNum(Integer parkNum) {
        for (ParkingLot parkingLot : ParkingLots) {
            if (parkingLot.getParkingLotNumber().equals(parkNum)) {
                return parkingLot;
            }
        }
        return null;
    }
}
