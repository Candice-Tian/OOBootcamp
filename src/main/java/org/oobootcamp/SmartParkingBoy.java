package org.oobootcamp;

import java.util.ArrayList;
import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        ParkingLots = parkingLots;
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        ParkingLots.sort(SmartParkingBoy.getParkingLotComparator);
        return ParkingLots.get(0);
    }
    private static final Comparator<ParkingLot> getParkingLotComparator =
            (o1, o2) -> o2.getFreeSpace() - o1.getFreeSpace();
}
