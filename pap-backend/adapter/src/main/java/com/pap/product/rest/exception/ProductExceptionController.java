package com.pap.product.rest.exception;

import com.pap.product.exception.ExceptionInitializer;
import com.pap.product.exception.InvalidPageOrSizeException;
import com.pap.product.exception.ProductNotFoundException;
import com.pap.product.model.CustomError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ProductExceptionController {
    private final ExceptionInitializer exceptionInitializer ;

    private Set<String> errorMessages ;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomError> handleProductNotFound(ProductNotFoundException exception){
        errorMessages =  new HashSet<>() ;
        errorMessages.add(exception.getMessage());
        return new ResponseEntity<>(exceptionInitializer.getException(errorMessages),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        errorMessages =  new HashSet<>() ;
        List<String> messages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        errorMessages.addAll(messages);
        return new ResponseEntity<>(exceptionInitializer.getException(errorMessages),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolation (ConstraintViolationException exception){
        errorMessages = new HashSet<>();
        exception.getConstraintViolations().forEach((constraintViolation -> errorMessages.add(constraintViolation.getMessage())));
        return new ResponseEntity<>(exceptionInitializer.getException(errorMessages),HttpStatus.BAD_REQUEST);
    }
}
