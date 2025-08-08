package in.ratansgh.services.implementation;

import in.ratansgh.entities.Vehicle;
import in.ratansgh.exception.InvalidVehicle;
import in.ratansgh.model.ParkingDetails;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingManager {

    private final SlotManager slotManager;
    private final HistoryService historyService;
    private final ReportService reportService;
    private final Map<String, ParkingDetails> activeParkings = new HashMap<>();
    private final Set<String> activeVehicleNumbers = new HashSet<>();

    public ParkingManager(int capacity) {
        slotManager = new SlotManager(capacity);
        historyService = new HistoryService();
        reportService = new ReportService();
    }

    public void park(String name, String number, String mobile, String type) {
        ValidationService.validateName(name);
        ValidationService.validateVehicleNumber(number);
        ValidationService.validateMobile(mobile);

        if (activeVehicleNumbers.contains(number)) {
            System.out.println("Vehicle already parked.");
            return;
        }

        Vehicle vehicle = VehicleFactory.createVehicle(type, name, number, mobile);
        int slot = slotManager.assignSlot();

        ParkingDetails details = new ParkingDetails(vehicle, slot);
        activeParkings.put(number, details);
        activeVehicleNumbers.add(number);
        historyService.record(number, details);

        System.out.println("Parked at slot: " + slot);
    }

    public void unpark(String number) {
        if (!activeParkings.containsKey(number)) {
            throw new InvalidVehicle("Vehicle not found in parking.");
        }

        ParkingDetails details = activeParkings.get(number);
        LocalDateTime now = LocalDateTime.now();
        double fee = FeeCalculatorService.calculateFee(details.getVehicle(), details.getEntryTime(), now);

        System.out.println("Vehicle No: " + number);
        System.out.println("Slot Freed: " + details.getSlot());
        System.out.println("Fee: ₹" + fee);

        slotManager.releaseSlot(details.getSlot());
        activeParkings.remove(number);
        activeVehicleNumbers.remove(number);
    }

    public void viewParkedVehicles() {
        if (activeParkings.isEmpty()) {
            System.out.println("No vehicles parked.");
            return;
        }
        activeParkings.values().forEach(d ->
                System.out.println("Slot: " + d.getSlot() + ", Vehicle: " + d.getVehicle().getVehicleNumber()));
    }

    public void viewHistory(String vehicleNumber) {
        List<ParkingDetails> records = historyService.getHistory(vehicleNumber);
        if (records.isEmpty()) {
            System.out.println("No history found.");
            return;
        }

        records.forEach(d ->
                System.out.println("Slot: " + d.getSlot() + ", Entry: " + d.getEntryTime()));
    }

    public void generateReports() {
        reportService.generateReports(new ArrayList<>(activeParkings.values()));
    }
}
