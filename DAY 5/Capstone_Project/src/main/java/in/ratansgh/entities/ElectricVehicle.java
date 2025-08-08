package in.ratansgh.entities;

/**
 * Electric vehicle class.
 * Implements fee calculation specific to electric vehicles.
 */
public class ElectricVehicle extends Vehicle {

    // Hourly rate for electric vehicles
    private static final double RATE_PER_HOUR = 5.0;

    public ElectricVehicle(String ownerName, String vehicleNumber, String mobileNumber) {
        super(ownerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        double ratePerMinute = RATE_PER_HOUR / 60.0;
        return Math.ceil(durationInMinutes * ratePerMinute);
    }
}
