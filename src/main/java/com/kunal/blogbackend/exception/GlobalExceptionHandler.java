package com.kunal.blogbackend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kunal.blogbackend.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Resource with given id not found");
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    // Resource already exist
    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<ApiResponse> resourceFoundExceptionHandler(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Resource already exist");
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }

    // validation error message
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
