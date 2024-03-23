package com.poc.diospringboot.service;

import com.poc.diospringboot.service.exception.ServiceException;

public interface ExistsByEmailService {
    public boolean execute(String email) throws ServiceException;
}
