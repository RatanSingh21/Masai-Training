package in.ratansgh.entities;

import java.time.LocalDateTime;

/**
 * Abstract class representing a generic vehicle.
 * Contains common fields and behaviors for all vehicle types.
 */
public abstract class Vehicle {
    protected String ownerName;
    protected String vehicleNumber;
    protected String mobileNumber;
    protected LocalDateTime entryTime;

    // Constructor initializes fields and sets entry time to now
    public Vehicle(String ownerName, String vehicleNumber, String mobileNumber) {
        this.ownerName = ownerName;
        this.vehicleNumber = vehicleNumber;
        this.mobileNumber = mobileNumber;
        this.entryTime = LocalDateTime.now(); // Capture timestamp
    }

    // Abstract method to be implemented by subclasses for fee calculation
    public abstract double calculateFee(long durationInMinutes);

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    // Override equals based on unique vehicle number
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleNumber.equals(vehicle.vehicleNumber);
    }

    // Hash based on vehicle number
    @Override
    public int hashCode() {
        return vehicleNumber.hashCode();
    }
}
