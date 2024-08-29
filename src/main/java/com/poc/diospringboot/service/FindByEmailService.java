package com.poc.diospringboot.service;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.service.exception.ServiceException;

public interface FindByEmailService {
    public User execute(final String email) throws ServiceException;
}
