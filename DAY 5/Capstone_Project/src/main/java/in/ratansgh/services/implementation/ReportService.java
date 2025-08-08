package in.ratansgh.services.implementation;

import in.ratansgh.entities.*;
import in.ratansgh.model.ParkingDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    public void generateReports(List<ParkingDetails> allActiveParkings) {
        System.out.println("\n--- 2-Wheelers parked > 3 hours ---");
        allActiveParkings.stream()
                .filter(p -> p.getVehicle() instanceof TwoWheeler)
                .filter(p -> Duration.between(p.getEntryTime(), LocalDateTime.now()).toHours() > 3)
                .forEach(p -> System.out.println("Vehicle No: " + p.getVehicle().getVehicleNumber()));

        System.out.println("\n--- Vehicle Count by Type ---");
        Map<String, Long> counts = allActiveParkings.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getVehicle() instanceof TwoWheeler) return "TwoWheeler";
                    if (p.getVehicle() instanceof FourWheeler) return "FourWheeler";
                    if (p.getVehicle() instanceof ElectricVehicle) return "ElectricVehicle";
                    return "Unknown";
                }, Collectors.counting()));

        counts.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}
