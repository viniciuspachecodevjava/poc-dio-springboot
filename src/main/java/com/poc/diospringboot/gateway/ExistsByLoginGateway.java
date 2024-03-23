package com.poc.diospringboot.gateway;

import com.poc.diospringboot.gateway.exception.GatewayException;

public interface ExistsByLoginGateway {
    boolean execute(final String login) throws GatewayException;
}
