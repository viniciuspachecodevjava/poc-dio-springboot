package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.CreateUserGateway;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;
import com.poc.diospringboot.service.CreateUserService;
import com.poc.diospringboot.service.ExistsByEmailService;
import com.poc.diospringboot.service.exception.CreateUserServiceException;
import com.poc.diospringboot.service.exception.ExistsByEmailServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImp implements CreateUserService {
    private final CreateUserGateway createUserGateway;
    private final ExistsByEmailService existsByEmailService;

    public CreateUserServiceImp(CreateUserGateway createUserGateway, ExistsByEmailService existsByEmailService) {
        this.createUserGateway = createUserGateway;
        this.existsByEmailService = existsByEmailService;
    }

    @Override
    public void execute(User user) throws ServiceException {
        checkIfEmailExists(user.getEmail());
        try {
            createUserGateway.execute(user);
        } catch (CreateUserGatewayException e) {
            throw new CreateUserServiceException("Erro ao cadastrar usuario");
        }
    }

    private void checkIfEmailExists(final String email) throws ServiceException {
        if (existsByEmailService.execute(email)) {
            throw new ExistsByEmailServiceException("O e-mail " + email + " já existe, tente outro.");
        }
    }

    private void checkIfLoginExists(String login) {

    }

    private void checkIfPasswordExists(String password) {

    }

}