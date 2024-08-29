package com.poc.diospringboot.service;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.service.exception.ServiceException;

public interface AuthenticationService {
    public String execute(User user) throws ServiceException;
}
