package org.oobootcamp;

import java.util.ArrayList;

public class GraduateParkingBoy extends ParkingBoy {


    public GraduateParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.ParkingLots = parkingLots;
    }

    @Override
    public ParkingLot getAvailableParkingLot(){
        return ParkingLots.stream().filter(p -> p.getFreeSpace() > 0).findFirst().orElse(null);
    }
}
