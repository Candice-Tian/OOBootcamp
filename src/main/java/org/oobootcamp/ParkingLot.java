package org.oobootcamp;

import java.util.ArrayList;

public class ParkingLot {
    private final Integer TotalCarSpace;

    public ParkingLot(Integer totalCarSpace) {
        TotalCarSpace = totalCarSpace;
    }

    public Integer getFreeSpace() {
        return TotalCarSpace - ParkedCars.size();
    }

    private ArrayList<String> ParkedCars = new ArrayList<>();

    public Result parking(String carNumber){
        Result result=new Result();
        if(getFreeSpace()>0){
            ParkedCars.add(carNumber);
            result.setCode("停车成功");
            Ticket ticket=new Ticket();
            ticket.setCarNum(carNumber);
            result.setTicket(ticket);
            return result;
        }
        return  null;

    }


}
