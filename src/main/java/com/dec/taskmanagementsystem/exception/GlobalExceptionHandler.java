package com.dec.taskmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The GlobalExceptionHandler class handles all exception.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exception when a resource not exists
     * @param resourceNotFoundException ResourceNotFoundException Object
     * @param request WebRequest Object
     * @return the ErrorDetails with HttpStatus 400
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                        WebRequest request){
        var errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFoundException.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param exception ApiException object
     * @param request WebRequest object
     * @return ErrorDetails with 400
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleTaskApiException(ApiException exception,
                                                               WebRequest request){
        var errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all exception occurs in method argument
     * @param exception MethodArgumentNotValidException object
     * @param request WebRequest object
     * @return ErrorDetails with 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                              WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}