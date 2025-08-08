package in.ratansgh.services.implementation;

import in.ratansgh.entities.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Calculates the fee for parking based on per-minute charges.
 */
public class FeeCalculatorService {

    public static double calculateFee(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime) {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return vehicle.calculateFee(minutes);
    }
}
