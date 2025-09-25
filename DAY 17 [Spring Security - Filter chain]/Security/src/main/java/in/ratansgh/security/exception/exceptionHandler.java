package in.ratansgh.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;


// always for exceptionable
@ControllerAdvice
public class exceptionHandler {


    // this is for invalid exception
    @ExceptionHandler
    public ResponseEntity<MyErrorDetails> myExceptionHandler(InvalidException ie, WebRequest req){
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ie.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    // like this there will be more methods for more exception like null exception and more
    // so we should have a genric error handler like if any unknow error happens

    @ExceptionHandler
    public ResponseEntity<MyErrorDetails> handleAllExceptions(Exception ex, WebRequest req) {
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("Internal Error: " + ex.getMessage());
        err.setDetails(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
