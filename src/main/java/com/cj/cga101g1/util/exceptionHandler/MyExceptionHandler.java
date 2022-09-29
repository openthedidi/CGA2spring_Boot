package com.cj.cga101g1.util.exceptionHandler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.swing.event.HyperlinkEvent;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("ResourceNotFoundException");
        errorDetail.setStatus(404);
        errorDetail.setZonedDateTime(ZonedDateTime.now());
        errorDetail.setPGMessage(e.getMessage());
        FindRequestURL findRequestURL = new FindRequestURL();
        errorDetail.setPath(findRequestURL.getURL(request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorDetail);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                               HttpStatus status,
//                                                               HttpServletRequest request){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTitle("ArgumentNotValid");
//        errorDetail.setStatus(status.value());
//        errorDetail.setZonedDateTime(ZonedDateTime.now());
//        errorDetail.setPGMessage(ex.getMessage());
//        FindRequestURL findRequestURL = new FindRequestURL();
//        errorDetail.setPath(findRequestURL.getURL(request));
//
//        Map<String, List<ValidationError>> map = errorDetail.getErrors();
//        List<FieldError> fieldErrorsList = ex.getBindingResult().getFieldErrors();
//        for(FieldError error:fieldErrorsList){
//            String fieldError = error.getField();
//            List<ValidationError> errors = map.get(fieldError);
//            if(errors==null){
//                errors =new ArrayList<>();
//                map.put("錯誤欄位",errors);
//            }
//
//            String errorCode= error.getCode();
//            String message =error.getDefaultMessage();
//            ValidationError validationError = new ValidationError(errorCode,message);
//            errors.add(validationError);
//        }
//
//        return ResponseEntity.status(status)
//                .body(errorDetail);
//    }




//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> exceptionDemo(RuntimeException e){
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//                .body("RuntimeException"+e.getMessage());
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> exceptionDemo(IllegalArgumentException e){
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//                .body("IllegalArgumentException"+e.getMessage());
//    }
}
