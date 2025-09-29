package in.ratansgh.Gradle_Build_Tool.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class errorManagement {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setMessage("Message by error handler: " + ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

}
