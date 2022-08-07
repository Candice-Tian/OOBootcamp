package org.oobootcamp;

import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {
    public static SmartParkingBoy BuildSmartParkingBoy(ParkingLot... parkingLots) {
        return new SmartParkingBoy(new ArrayList<>(Arrays.asList(parkingLots)));
    }

    public static GraduateParkingBoy BuildGraduateParkingBoy(ParkingLot... parkingLots) {
        return new GraduateParkingBoy(new ArrayList<>(Arrays.asList(parkingLots)));
    }
}
