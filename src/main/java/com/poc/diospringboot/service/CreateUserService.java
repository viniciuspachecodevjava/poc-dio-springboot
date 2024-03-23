package com.poc.diospringboot.service;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.service.exception.ServiceException;

public interface CreateUserService {
    public void execute(final User user) throws ServiceException;
}
