package com.poc.diospringboot.api.controller.exception;

public class IncorrectPasswordException extends ControllerException{
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
