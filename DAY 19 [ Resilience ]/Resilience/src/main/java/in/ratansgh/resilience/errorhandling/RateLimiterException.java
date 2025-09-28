package in.ratansgh.resilience.errorhandling;

public class RateLimiterException extends RuntimeException {
    public RateLimiterException(String message) {
        super(message);
    }
}
