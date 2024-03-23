package com.poc.diospringboot.gateway.database;

import com.poc.diospringboot.gateway.ExistsByEmailGateway;
import com.poc.diospringboot.gateway.database.repository.UserRepository;
import com.poc.diospringboot.gateway.exception.ExistsByEmailGatewayException;
import com.poc.diospringboot.gateway.exception.GatewayException;
import org.springframework.stereotype.Component;

@Component
public class ExistsByEmailGatewayPostegresql implements ExistsByEmailGateway {
    UserRepository userRepository;

    public ExistsByEmailGatewayPostegresql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean execute(String email) throws GatewayException {
        try {
            return userRepository.existsByEmail(email);
        }catch (Exception e){
            throw new ExistsByEmailGatewayException("Erro ao verificar email existente", e);
        }
    }
}
