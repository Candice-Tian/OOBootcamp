package org.oobootcamp;

import java.util.ArrayList;

public class GraduateParkingBoy {
    private final ArrayList<ParkingLot> ParkingLots;

    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    public Result parking(String carNumber) {
        Result result = new Result();
        for(ParkingLot parkingLot: ParkingLots){
            if (parkingLot.getFreeSpace() > 0) {
                result = parkingLot.parking(carNumber);
                result.getTicket().setParkNum(parkingLot.getParkingLotNumber());
            }
        }
        return result;
    }
}
