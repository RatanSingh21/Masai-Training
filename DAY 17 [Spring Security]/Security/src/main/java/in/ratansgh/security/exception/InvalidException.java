package in.ratansgh.security.exception;

// will run only while runtime
public class InvalidException extends RuntimeException {

    // constructor
    public InvalidException(String message) {
        super(message);
    }
}
