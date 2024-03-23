package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.gateway.ExistsByEmailGateway;
import com.poc.diospringboot.gateway.exception.ExistsByEmailGatewayException;
import com.poc.diospringboot.gateway.exception.GatewayException;
import com.poc.diospringboot.service.exception.ExistsByEmailServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ExistsByEmailServiceImp implements com.poc.diospringboot.service.ExistsByEmailService {

    private final ExistsByEmailGateway existsByEmailGateway;

    public ExistsByEmailServiceImp(ExistsByEmailGateway existsByEmailGateway) {
        this.existsByEmailGateway = existsByEmailGateway;
    }

    @Override
    public boolean execute(final String email) throws ServiceException {
        try {
            return existsByEmailGateway.execute(email);
        }catch (GatewayException e){
            throw new ExistsByEmailServiceException("Erro no serviço de verificação de duplicidade", e);
        }
    }
}
