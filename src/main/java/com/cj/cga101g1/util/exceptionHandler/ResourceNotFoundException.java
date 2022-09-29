package com.cj.cga101g1.util.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message,Throwable cause){
        super(message);
    }
}