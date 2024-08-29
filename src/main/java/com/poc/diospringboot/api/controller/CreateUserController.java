package com.poc.diospringboot.api.controller;

import com.poc.diospringboot.api.request.user.UserRequest;
import com.poc.diospringboot.api.response.user.UserResponse;
import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.infra.security.TokenService;
import com.poc.diospringboot.service.CreateUserService;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.ExistsByEmailServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class CreateUserController {
    private final CreateUserService createUserService;

    private final FindByEmailService findByEmailService;

    private final TokenService tokenService;

    public CreateUserController(CreateUserService createUserService, FindByEmailService findByEmailService, TokenService tokenService) {
        this.createUserService = createUserService;
        this.findByEmailService = findByEmailService;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity execute(@RequestBody @Valid UserRequest userRequest) throws ServiceException {
        try {
            User userRegistered = createUserService.execute(userRequest.toDomain());
            String token = tokenService.generateToken(userRegistered);
            return ResponseEntity.ok(new UserResponse(userRegistered.getEmail(), token));
        } catch (ServiceException e) {
            throw new ExistsByEmailServiceException(e.getMessage());
        }
    }
}
