package com.cj.cga101g1.util.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;
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
    private Map<String, List<ValidationError>> errors;
}
