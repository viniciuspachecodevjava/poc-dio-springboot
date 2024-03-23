package com.poc.diospringboot.gateway;

import com.poc.diospringboot.gateway.exception.GatewayException;

public interface ExistsByEmailGateway {
    boolean execute(final String email) throws GatewayException;
}
