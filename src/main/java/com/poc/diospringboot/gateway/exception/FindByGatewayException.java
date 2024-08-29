package com.poc.diospringboot.gateway.exception;

public class FindByGatewayException extends GatewayException{
    public FindByGatewayException(Throwable cause) {
        super(cause);
    }

    public FindByGatewayException(String message) {
        super(message);
    }

    public FindByGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
