package com.poc.diospringboot.gateway;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;

public interface CreateUserGateway {
    public void execute(final User user) throws CreateUserGatewayException;
}
