package in.teamPlan.secure_auth_api.util.errorhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptions.InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentials(
            CustomExceptions.InvalidCredentialsException ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid Credentials",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomExceptions.TokenExpiredException.class)
    public ResponseEntity<ErrorDetails> handleTokenExpired(
            CustomExceptions.TokenExpiredException ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.UNAUTHORIZED.value(),
                "Token Expired",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomExceptions.InvalidTokenException.class)
    public ResponseEntity<ErrorDetails> handleInvalidToken(
            CustomExceptions.InvalidTokenException ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid Token",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Input validation failed",
                request.getDescription(false).replace("uri=", "")
        );
        errorDetails.setValidationErrors(validationErrors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        List<String> validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                "Validation constraint violated",
                request.getDescription(false).replace("uri=", "")
        );
        errorDetails.setValidationErrors(validationErrors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
