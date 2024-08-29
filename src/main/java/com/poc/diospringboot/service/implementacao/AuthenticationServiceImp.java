package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.api.request.user.UserRequest;
import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.infra.security.TokenService;
import com.poc.diospringboot.service.AuthenticationService;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.IncorrectPasswordServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final FindByEmailService findByEmailService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthenticationServiceImp(FindByEmailService findByEmailService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.findByEmailService = findByEmailService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String execute(User user) throws ServiceException {
        verifyIfEmailImputIsNullOrEmpty(user.getEmail());
        verifyIfPasswordInputIsNullOrEmpty(user.getPassword());
        User userFinded = findByEmailService.execute(user.getEmail());
        comparePassword(user.getPassword(), userFinded.getPassword());

        return tokenService.generateToken(user);
    }

    private void verifyIfEmailImputIsNullOrEmpty(String email) throws ServiceException {
        if (email == null || email.isEmpty()) {
            throw new ServiceException("Campo e-mail não pode estar vazio");
        }
    }

    private void verifyIfPasswordInputIsNullOrEmpty(String password) throws ServiceException {
        if (password == null || password == "") {
            throw new ServiceException("Campo password não pode estar vazio");
        }
    }

    private void comparePassword(String passwordBody, String passwordUser) throws IncorrectPasswordServiceException {
        if (!passwordEncoder.matches(passwordBody, passwordUser)) {
            throw new IncorrectPasswordServiceException("Senha incorreta!");
        }
    }
}
