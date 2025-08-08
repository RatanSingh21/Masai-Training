package in.ratansgh.entities;

/**
 * Two-wheeler vehicle class.
 * Implements fee calculation specific to two-wheelers.
 */
public class TwoWheeler extends Vehicle {

    // Hourly rate for two-wheelers
    private static final double RATE_PER_HOUR = 10.0;

    public TwoWheeler(String ownerName, String vehicleNumber, String mobileNumber) {
        super(ownerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        double ratePerMinute = RATE_PER_HOUR / 60.0;
        return Math.ceil(durationInMinutes * ratePerMinute);
    }
}
