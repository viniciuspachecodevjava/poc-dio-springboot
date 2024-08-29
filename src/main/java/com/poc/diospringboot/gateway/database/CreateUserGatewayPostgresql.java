package com.poc.diospringboot.gateway.database;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.CreateUserGateway;
import com.poc.diospringboot.gateway.database.model.UserDatabase;
import com.poc.diospringboot.gateway.database.repository.UserRepository;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;
import org.springframework.stereotype.Component;

@Component
public class CreateUserGatewayPostgresql implements CreateUserGateway {
    UserRepository userRepository;

    public CreateUserGatewayPostgresql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User user) throws CreateUserGatewayException {
        try {
           UserDatabase userDatabase = userRepository.save(new UserDatabase(user));
            return userDatabase.toUser();
        }catch (Exception e){
            throw new CreateUserGatewayException("Erro ao salvar um novo usuário", e);
        }
    }
}
