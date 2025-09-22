package in.ratansgh.Assignment_LMS.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler
    public ResponseEntity<myErrorDetails> handleAllExceptions(Exception ex, WebRequest req) {
        myErrorDetails err = new myErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("Internal Error: " + ex.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
