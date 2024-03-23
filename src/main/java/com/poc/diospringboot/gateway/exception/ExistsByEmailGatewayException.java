package com.poc.diospringboot.gateway.exception;

public class ExistsByEmailGatewayException extends GatewayException{
    public ExistsByEmailGatewayException(Throwable cause) {
        super(cause);
    }

    public ExistsByEmailGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
