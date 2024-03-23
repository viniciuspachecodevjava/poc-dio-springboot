package com.poc.diospringboot.gateway.database;

import com.poc.diospringboot.gateway.ExistsByLoginGateway;
import com.poc.diospringboot.gateway.database.repository.UserRepository;
import com.poc.diospringboot.gateway.exception.ExistsByLoginGatewayException;
import com.poc.diospringboot.gateway.exception.GatewayException;
import org.springframework.stereotype.Component;

@Component
public class ExistsByLoginGatewayPostgresql implements ExistsByLoginGateway {
    UserRepository userRepository;

    public ExistsByLoginGatewayPostgresql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean execute(final String login) throws GatewayException {
        try {
            return userRepository.existsByLogin(login);
        }catch (Exception e){
            throw new ExistsByLoginGatewayException("Erro ao verificar duplicidade de login", e);
        }
    }
}
