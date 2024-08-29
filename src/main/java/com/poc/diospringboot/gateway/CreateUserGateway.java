package com.poc.diospringboot.gateway;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;

public interface CreateUserGateway {
    public User execute(final User user) throws CreateUserGatewayException;
}
