package com.kunal.blogbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kunal.blogbackend.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Resource with given id not found");
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<ApiResponse> resourceFoundExceptionHandler(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Resource already exist");
        apiResponse.setSuccess(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.CONFLICT);
    }
}
