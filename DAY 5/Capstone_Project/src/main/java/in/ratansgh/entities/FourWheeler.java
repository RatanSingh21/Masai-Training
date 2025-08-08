package in.ratansgh.entities;

/**
 * Four-wheeler vehicle class.
 * Implements fee calculation specific to four-wheelers.
 */
public class FourWheeler extends Vehicle {

    // Hourly rate for four-wheelers
    private static final double RATE_PER_HOUR = 20.0;

    public FourWheeler(String ownerName, String vehicleNumber, String mobileNumber) {
        super(ownerName, vehicleNumber, mobileNumber);
    }

    @Override
    public double calculateFee(long durationInMinutes) {
        double ratePerMinute = RATE_PER_HOUR / 60.0;
        return Math.ceil(durationInMinutes * ratePerMinute);
    }
}
