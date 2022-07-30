package org.oobootcamp;

public class Ticket {
    public Ticket(String carNum) {
        this.carNum = carNum;
    }

    private String carNum;

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
