package com.poc.diospringboot.gateway.database;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.FindByEmailGateway;
import com.poc.diospringboot.gateway.database.model.UserDatabase;
import com.poc.diospringboot.gateway.database.repository.UserRepository;
import com.poc.diospringboot.gateway.exception.FindByGatewayException;
import com.poc.diospringboot.gateway.exception.GatewayException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindByEmailGatewayPostgresql implements FindByEmailGateway {
    private final UserRepository userRepository;

    public FindByEmailGatewayPostgresql(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(final String email) throws GatewayException {

        Optional<UserDatabase> userDatabase = userRepository.findByEmail(email);

            if (userDatabase.isPresent()) {
                UserDatabase user = userDatabase.get();

                List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        .collect(Collectors.toList());
                // Utilize os dados do usuário, como nome, sobrenome, etc.
                return new User(user.getId(), user.getName(), user.getEmail(), user.getLogin(), user.getPassword(), grantedAuthorities);
            } else {
                // Lançando exceção se o usuário não for encontrado
                throw new FindByGatewayException("Usuário não encontrado para o email: " + email);
            }
    }
}