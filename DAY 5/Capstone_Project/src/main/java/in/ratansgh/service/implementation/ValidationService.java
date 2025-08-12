package in.ratansgh.service.implementation;

import in.ratansgh.exception.*;

import java.util.regex.Pattern;

/**
 * Validates user inputs like names, vehicle numbers, and mobile numbers.
 */
public class ValidationService {

    public static void validateName(String name) {
        if (!Pattern.matches("^[A-Za-z ]+$", name)) {
            throw new IllegalArgumentException("Invalid name. Only alphabets allowed.");
        }
    }

    public static void validateVehicleNumber(String number) {
        if (!Pattern.matches("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", number)) {
            throw new InvalidVehicleNumberException("Invalid vehicle number format.");
        }
    }

    public static void validateMobile(String mobile) {
        if (!Pattern.matches("^\\d{10}$", mobile)) {
            throw new InvalidMobileNumberException("Invalid mobile number.");
        }
    }
}
