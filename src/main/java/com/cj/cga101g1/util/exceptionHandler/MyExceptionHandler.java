package com.cj.cga101g1.util.exceptionHandler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("ArgumentNotValid");
        errorDetail.setStatus(status.value());
        errorDetail.setZonedDateTime(ZonedDateTime.now());
        errorDetail.setPGMessage(ex.getMessage());
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        errorDetail.setPath(path);

        /**
         * Map<String, List<ValidationError>> = < 驗證的欄位名稱(如遊戲名稱), 該欄位驗證沒有通過的錯誤，因為可能不只一個，所以用List
         * */
        Map<String, List<ValidationError>> errorsMap = errorDetail.getErrors();

        /**
        *    FieldError為每一個驗證失敗的欄位
        * */
        List<FieldError> fieldErrorsList = ex.getBindingResult().getFieldErrors();
        for(FieldError error:fieldErrorsList){
            String fieldError = error.getField();
            List<ValidationError> errors = errorsMap.get(fieldError);
            if(errors==null){
                errors =new ArrayList<>();
                errorsMap.put(fieldError,errors);
            }

            String errorCode= error.getCode();
            String message =error.getDefaultMessage();
            ValidationError validationError = new ValidationError(errorCode,message);
            errors.add(validationError);
        }

        return ResponseEntity.status(status)
                .body(errorDetail);

    }




    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionDemo(RuntimeException e){
        System.out.println("啟動 MyExceptionHandler - exceptionDemo -RuntimeException");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("RuntimeException"+e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionDemo(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("IllegalArgumentException"+e.getMessage());
    }
}
