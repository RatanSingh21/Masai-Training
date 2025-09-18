package in.ratansgh.JWT_R4J.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionManagement {

//    handles any exception since we have passed Exception class
    @ExceptionHandler
    public ResponseEntity<MyErrorDetails> handleAllExceptions(Exception ex, WebRequest req) {
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("Internal Error: " + ex.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
