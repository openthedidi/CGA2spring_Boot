package com.cj.cga101g1.util.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ErrorDetail {
    private String title;
    private int status;
    private ZonedDateTime zonedDateTime;
    private String PGMessage;
    private String path;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
    private List<String> error;

}
