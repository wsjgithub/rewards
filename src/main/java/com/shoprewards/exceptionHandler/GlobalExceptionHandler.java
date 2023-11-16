package com.shoprewards.exceptionHandler;

import com.shoprewards.exception.CustomerNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFoundExceptionHandler(CustomerNotFoundException ex){
        return new ResponseEntity<String>("Customer doesn't exist.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationExceptionHandler(ValidationException ex){
        return new ResponseEntity<String>("Input is not valid.", HttpStatus.BAD_REQUEST);
    }
}
