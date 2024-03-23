package com.poc.diospringboot.service;

import com.poc.diospringboot.service.exception.ServiceException;

public interface ExistsByLoginService {
    boolean execute(final String login) throws ServiceException;
}
