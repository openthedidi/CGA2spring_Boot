package com.cj.cga101g1.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionDemo(RuntimeException e){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("RuntimeException"+e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionDemo(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("IllegalArgumentException"+e.getMessage());
    }
}
