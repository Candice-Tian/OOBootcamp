package org.oobootcamp;

public class Ticket {
    public Ticket(String carNum, Integer parkNum) {
        CarNum = carNum;
        ParkNum = parkNum;
    }

    private String CarNum;

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }


    private Integer ParkNum;

    public Integer getParkNum() {
        return ParkNum;
    }

    public void setParkNum(Integer parkNum) {
        ParkNum = parkNum;
    }
}
