package com.cj.cga101g1.util.exceptionHandler;

import lombok.Getter;

@Getter
public class ValidationError {
    private String code;
    private String message;

    public ValidationError(String code,String message){
        super();
        this.code =code;
        this.message=message;
    };
}
