package org.oobootcamp;

import java.util.ArrayList;

public class ParkingLot {
    private Integer TotalCarSpace;

    public ParkingLot(Integer totalCarSpace) {
        TotalCarSpace = totalCarSpace;
    }

    public Integer getFreeSpace() {
        return TotalCarSpace - ParkedCars.size();
    }

    private ArrayList<String> ParkedCars=new ArrayList<>();

    public String parking(String carNumber){
        if(getFreeSpace()==0){
            return "failed";
        }
        ParkedCars.add(carNumber);
        return "succeed";
    }
}
