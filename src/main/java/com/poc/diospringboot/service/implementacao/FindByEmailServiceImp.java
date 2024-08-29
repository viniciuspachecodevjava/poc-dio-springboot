package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.FindByEmailGateway;
import com.poc.diospringboot.gateway.exception.GatewayException;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.FindByEmailServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class FindByEmailServiceImp implements FindByEmailService {
    private final FindByEmailGateway findByEmailGateway;

    public FindByEmailServiceImp(FindByEmailGateway findByEmailGateway) {
        this.findByEmailGateway = findByEmailGateway;
    }

    @Override
    public User execute(final String email) throws ServiceException {
        try {
            return findByEmailGateway.execute(email);
        }catch (GatewayException e){
            throw new FindByEmailServiceException(e.getMessage());
        }
    }
}
