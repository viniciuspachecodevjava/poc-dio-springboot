package com.poc.diospringboot.gateway.exception;

public class CreateUserGatewayException extends GatewayException{
    public CreateUserGatewayException(Throwable cause) {
        super(cause);
    }

    public CreateUserGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
