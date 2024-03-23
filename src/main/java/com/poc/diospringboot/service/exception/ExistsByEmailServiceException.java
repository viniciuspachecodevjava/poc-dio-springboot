package com.poc.diospringboot.service.exception;

public class ExistsByEmailServiceException extends ServiceException{
    public ExistsByEmailServiceException(String message) {
        super(message);
    }

    public ExistsByEmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
