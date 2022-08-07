package org.oobootcamp;

import org.oobootcamp.Exception.InvalidTicketException;
import org.oobootcamp.Exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends GraduateParkingBoy implements IManager {
    protected ArrayList<ParkingBoy> ParkingBoys = new ArrayList<>();

    public ParkingManager(ArrayList<ParkingBoy> parkingBoys, ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
        ParkingBoys.add(this);
        ParkingBoys.addAll(parkingBoys);
    }


    public Ticket manageParking(Car car) throws ParkingLotFullException {
        for (ParkingBoy parkingBoy : ParkingBoys) {
            ParkingLot parkingLot = parkingBoy.getAvailableParkingLot();
            if (parkingLot != null) {
                return parkingBoy.parking(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car managePicking(Ticket ticket) throws InvalidTicketException {
        for (ParkingBoy parkingBoy : ParkingBoys) {
           try{
             return parkingBoy.picking(ticket);

           }catch (InvalidTicketException ex){
           }
        }
        throw new InvalidTicketException();
    }
}
