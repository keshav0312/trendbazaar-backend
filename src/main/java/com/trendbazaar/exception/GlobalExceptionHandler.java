package com.trendbazaar.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ObjectError err;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req) {

        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach((err) -> {
            sb.append(err.getField()).append(": ").append(err.getDefaultMessage());
        });
        LocalDateTime  timestamp = LocalDateTime.now();
        String message = sb.toString();
        String error = "validation error";
        int status = HttpStatus.BAD_REQUEST.value();
        String path = req.getRequestURI();

        ApiError apiError = new ApiError(timestamp,status,message,error,path);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getMessage());
        LocalDateTime  timestamp = LocalDateTime.now();
        String message = sb.toString();
        String error = "resource not found";
        int status = HttpStatus.NOT_FOUND.value();
        String path = req.getRequestURI();
        ApiError apiError = new ApiError(timestamp,status,message,error,path);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getMessage());
        LocalDateTime  timestamp = LocalDateTime.now();
        String message = ex.getMessage();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String path = req.getRequestURI();
        ApiError apiError = new ApiError(timestamp,status,message,path,null);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


