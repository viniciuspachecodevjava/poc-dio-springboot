package com.poc.diospringboot.service.exception;

public class CreateUserServiceException extends ServiceException{
    public CreateUserServiceException(Throwable cause) {
        super(cause);
    }

    public CreateUserServiceException(String message) {
        super(message);
    }

    public CreateUserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
