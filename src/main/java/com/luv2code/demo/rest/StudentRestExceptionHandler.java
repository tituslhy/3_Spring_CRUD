package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Add a ControllerAdvice class to pre-process/handle exceptions
 * This is best practice because controller advice classes are always routed to this
 * class before and after pinging the endpoints.
 */
@ControllerAdvice
public class StudentRestExceptionHandler {

    /**
     * Add an exception handler to catch StudentNotFoundException
     * @param exc
     * @return 404 not found error
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * This exception handler handles incorrect input errors. Like passing a string into an
     * integer path variable. Handles edge cases.
     * @param exc
     * @return 400 bad request error
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
