package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.gateway.ExistsByLoginGateway;
import com.poc.diospringboot.gateway.exception.GatewayException;
import com.poc.diospringboot.service.ExistsByLoginService;
import com.poc.diospringboot.service.exception.ExistsByLoginServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ExistsByLoginServiceImp implements ExistsByLoginService {
    private final ExistsByLoginGateway existsByLoginGateway;

    public ExistsByLoginServiceImp(ExistsByLoginGateway existsByLoginGateway) {
        this.existsByLoginGateway = existsByLoginGateway;
    }

    @Override
    public boolean execute(final String login) throws ServiceException {
        try {
            return existsByLoginGateway.execute(login);
        }catch (GatewayException e){
            throw new ExistsByLoginServiceException("Erro ao verificar duplicidade de login", e);
        }
    }
}
