package swag.rest.bank_app_delivery.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentNotFoundAdvice {
//    @ResponseBody
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    String studentNotFound(Exception ex) {
//        return ex.getMessage();
//    }

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String fiveHundred(Exception ex) {
        return ex.getMessage();
    }
}