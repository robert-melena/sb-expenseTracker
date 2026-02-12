package com.expensetracker.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodNotValidException(MethodArgumentNotValidException e){
        //to hold all the responses in a map
        Map<String,String> responses = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            responses.put(fieldName,message);
        });

        //will return a response entity with all key values fieldName mapped with their correlating errors
        return new ResponseEntity<Map<String,String>>(responses, HttpStatus.BAD_REQUEST);

    }
}
