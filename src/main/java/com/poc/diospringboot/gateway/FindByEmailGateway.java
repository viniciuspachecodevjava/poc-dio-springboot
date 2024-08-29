package com.poc.diospringboot.gateway;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.exception.GatewayException;

public interface FindByEmailGateway {
    public User execute(final String email) throws GatewayException;
}
