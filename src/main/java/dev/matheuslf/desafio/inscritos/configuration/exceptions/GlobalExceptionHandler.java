package dev.matheuslf.desafio.inscritos.configuration.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request){
        ErrorResponse internalServerError = new ErrorResponse(
                List.of(ex.getMessage() != null ? ex.getMessage() : "Unexpected error"),
                "500",
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                "INTERNAL_SERVER_ERROR"
        );

        return ResponseEntity.status(500).body(internalServerError);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                List.of(ex.getMessage() != null ? ex.getMessage() : "Illegal argument"),
                "400",
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                "BAD_REQUEST"
        );
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fe) {
                        return fe.getField() + ": " + fe.getDefaultMessage();
                    } else {
                        return error.getDefaultMessage();
                    }
                })
                .toList();

        ErrorResponse response = new ErrorResponse(
                errors,
                "400",
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                "BAD_REQUEST"
        );
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                List.of("Page Not Found"),
                "404",
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                "NOT_FOUND"
        );
        return ResponseEntity.status(404).body(response);
    }
}
