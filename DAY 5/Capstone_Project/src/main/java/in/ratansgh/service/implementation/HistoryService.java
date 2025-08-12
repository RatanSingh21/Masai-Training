package in.ratansgh.service.implementation;

import in.ratansgh.model.ParkingDetails;

import java.util.*;

public class HistoryService {
    private final Map<String, List<ParkingDetails>> history = new HashMap<>();

    public void record(String vehicleNumber, ParkingDetails details) {
        history.computeIfAbsent(vehicleNumber, k -> new ArrayList<>()).add(details);
    }

    public List<ParkingDetails> getHistory(String vehicleNumber) {
        return history.getOrDefault(vehicleNumber, new ArrayList<>());
    }
}
