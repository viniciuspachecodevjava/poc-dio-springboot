package com.poc.diospringboot.infra.security.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.gateway.exception.GatewayException;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.FindByEmailServiceException;
import com.poc.diospringboot.service.exception.ServiceException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImp implements UserDetailsService {
    private final FindByEmailService findByEmailService;

    public CustomUserDetailsServiceImp(FindByEmailService findByEmailService) {
        this.findByEmailService = findByEmailService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user;

        try {
            user = findByEmailService.execute(email);

        }  catch (ServiceException e) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getGrantedAuthorities());
    }
}
