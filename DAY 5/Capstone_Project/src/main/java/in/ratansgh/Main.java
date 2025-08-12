package in.ratansgh;

import in.ratansgh.service.implementation.ParkingManager;

public class Main {
    public static void main(String[] args) {

        ParkingManager manager = new ParkingManager(3); // small capacity for test

        // 1.  Park valid TwoWheeler
        try {
            System.out.println("\n-- Park TwoWheeler --");
            manager.park("Alice", "MH12AB1234", "9876543210", "TwoWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 2.  Park valid FourWheeler
        try {
            System.out.println("\n-- Park FourWheeler --");
            manager.park("Bob", "DL05CD4321", "9123456789", "FourWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 3.  Park valid Electric
        try {
            System.out.println("\n-- Park Electric Vehicle --");
            manager.park("Charlie", "KA03EV9999", "9988776655", "Electric");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 4.  Duplicate vehicle park
        try {
            System.out.println("\n-- Duplicate Vehicle Park --");
            manager.park("Alice", "MH12AB1234", "9876543210", "TwoWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 5.  Invalid vehicle number
        try {
            System.out.println("\n-- Invalid Vehicle Number --");
            manager.park("David", "1234MHAB", "9876543210", "TwoWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 6.  Invalid mobile number
        try {
            System.out.println("\n-- Invalid Mobile Number --");
            manager.park("Emily", "TN10GH1234", "9999", "FourWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 7.  Invalid name
        try {
            System.out.println("\n-- Invalid Owner Name --");
            manager.park("John123", "GJ05JK1122", "9876543210", "Electric");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 8.  Parking lot full
        try {
            System.out.println("\n-- Parking Lot Full Test --");
            manager.park("Eve", "PB08LM1234", "9123456789", "TwoWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 9.  View all parked vehicles
        System.out.println("\n-- View All Parked Vehicles --");
        manager.viewParkedVehicles();

        // 10.  Generate Admin Reports
        System.out.println("\n-- Admin Reports --");
        manager.generateReports();

        // 11.  View vehicle history
        System.out.println("\n-- View History of MH12AB1234 --");
        manager.viewHistory("MH12AB1234");

        // 12.  Unpark MH12AB1234
        try {
            System.out.println("\n-- Unparking MH12AB1234 --");
            manager.unpark("MH12AB1234");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 13. Try to unpark again (not found)
        try {
            System.out.println("\n-- Invalid Unpark (Again) --");
            manager.unpark("MH12AB1234");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 14. Re-park into freed slot
        try {
            System.out.println("\n-- Reuse Freed Slot --");
            manager.park("Zack", "UP16ZZ9999", "9876501234", "FourWheeler");
        } catch (Exception e) {
            System.out.println(" " + e.getMessage());
        }

        // 15. Final Parked Vehicles
        System.out.println("\n-- Final Parked Vehicles --");
        manager.viewParkedVehicles();

        // 16. Final History
        System.out.println("\n-- Final History for UP16ZZ9999 --");
        manager.viewHistory("UP16ZZ9999");

        System.out.println("======= STATIC TEST END =======");
    }
}
