package in.ratansgh.service.implementation;

import in.ratansgh.entities.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String name, String number, String mobile) {

        switch (type.toLowerCase()) {
            case "twowheeler":
                return new TwoWheeler(name, number, mobile);
            case "fourwheeler":
                return new FourWheeler(name, number, mobile);
            case "electric":
                return new ElectricVehicle(name, number, mobile);
            default:
                throw new IllegalArgumentException("Invalid vehicle type.");
        }
    }
}
