package com.poc.diospringboot.gateway.exception;

public class ExistsByLoginGatewayException extends GatewayException{
    public ExistsByLoginGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
