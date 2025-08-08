package in.ratansgh.model;

import in.ratansgh.entities.Vehicle;

import java.time.LocalDateTime;

public class ParkingDetails {
    private Vehicle vehicle;
    private int slot;
    private LocalDateTime entryTime;

    public ParkingDetails(Vehicle vehicle, int slot) {
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = vehicle.getEntryTime();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
