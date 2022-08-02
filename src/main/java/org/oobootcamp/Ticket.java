package org.oobootcamp;

public class Ticket {
    public Ticket(String carNum) {
        CarNum = carNum;
    }

    private String CarNum;

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }
}
