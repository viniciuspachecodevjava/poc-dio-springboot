package com.poc.diospringboot.config.handling;

import com.poc.diospringboot.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<GenericErrorResponse> handle(ServiceException exception) {
        if (exception instanceof CreateUserServiceException) {
            int code = HttpStatus.BAD_REQUEST.value();
            String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
            String errormessage = exception.getMessage();
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse(code, error, errormessage);
            return ResponseEntity.status(code).body(genericErrorResponse);
        } else if (exception instanceof ExistsByEmailServiceException) {
            int code = HttpStatus.CONFLICT.value();
            String error = HttpStatus.CONFLICT.getReasonPhrase();
            String errormessage = exception.getMessage();
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse(code, error, errormessage);
            return ResponseEntity.status(code).body(genericErrorResponse);
        } else if (exception instanceof ExistsByLoginServiceException) {
            int code = HttpStatus.CONFLICT.value();
            String error = HttpStatus.CONFLICT.getReasonPhrase();
            String errormessage = exception.getMessage();
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse(code, error, errormessage);
            return ResponseEntity.status(code).body(genericErrorResponse);
        }else if (exception instanceof IncorrectPasswordServiceException){
            int code = HttpStatus.BAD_REQUEST.value();
            String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
            String errormessage = exception.getMessage();
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse(code, error, errormessage);
            return ResponseEntity.status(code).body(genericErrorResponse);
        }
        int code = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String errormessage = exception.getMessage();
        GenericErrorResponse genericErrorResponse = new GenericErrorResponse(code, error, errormessage);
        return ResponseEntity.status(code).body(genericErrorResponse);

    }

}
