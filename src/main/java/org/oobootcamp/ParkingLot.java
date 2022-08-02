package org.oobootcamp;

import java.util.ArrayList;

public class ParkingLot {
    private final Integer ParkingLotNumber;
    private final Integer capacity;

    public ParkingLot(Integer parkingLotNumber, Integer capacity) {
        ParkingLotNumber = parkingLotNumber;
        this.capacity = capacity;
    }

    public Integer getParkingLotNumber() {
        return ParkingLotNumber;
    }

    public Integer getFreeSpace() {
        return capacity - ParkedCars.size();
    }

    private ArrayList<String> ParkedCars = new ArrayList<>();

    public Result parking(String carNumber) {
        Result result = new Result();
        if (getFreeSpace() > 0) {
            ParkedCars.add(carNumber);
            result.setCode("停车成功");
            Ticket ticket = new Ticket(carNumber,1);
            result.setTicket(ticket);
        } else {
            result.setCode("停车失败, 停车位已满");
        }
        return result;
    }


    public Result unparking(Ticket ticket) {
        Result result = new Result();
        if (isTicketValid(ticket)) {
            ParkedCars.remove(ticket.getCarNum());
            result.setCode("取车成功");
        } else {
            result.setCode("取车失败, 停车票无效");
        }
        return result;
    }

    private Boolean isTicketValid(Ticket ticket) {
        return ParkedCars.contains(ticket.getCarNum());
    }
}
