package com.poc.diospringboot.service.exception;

public class FindByEmailServiceException extends ServiceException{
    public FindByEmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindByEmailServiceException(String message) {
        super(message);
    }

    public FindByEmailServiceException(Throwable cause) {
        super(cause);
    }
}
