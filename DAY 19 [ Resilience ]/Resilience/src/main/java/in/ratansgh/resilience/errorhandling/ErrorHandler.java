package in.ratansgh.resilience.errorhandling;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage("Message by error handler: " + ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<MyErrorDetails> handleRateLimitExceptions(RequestNotPermitted ex, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage("Rate limit exceeded: " + ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.TOO_MANY_REQUESTS); // 429
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<MyErrorDetails> handleCircuitBreakerExceptions(CallNotPermittedException ex, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage("Circuit breaker is open: " + ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE); // 503
    }

}