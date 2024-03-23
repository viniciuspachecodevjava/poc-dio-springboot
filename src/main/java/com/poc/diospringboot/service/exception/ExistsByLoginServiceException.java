package com.poc.diospringboot.service.exception;

public class ExistsByLoginServiceException extends ServiceException{
    public ExistsByLoginServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsByLoginServiceException(String message) {
        super(message);
    }
}
