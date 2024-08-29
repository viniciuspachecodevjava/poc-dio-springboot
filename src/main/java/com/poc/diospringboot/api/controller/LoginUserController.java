package com.poc.diospringboot.api.controller;

import com.poc.diospringboot.api.controller.exception.ControllerException;
import com.poc.diospringboot.api.controller.exception.IncorrectPasswordException;
import com.poc.diospringboot.api.request.user.UserLoginDTO;
import com.poc.diospringboot.api.request.user.UserRequest;
import com.poc.diospringboot.api.response.user.UserResponse;
import com.poc.diospringboot.api.response.user.UserResponseDTO;
import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.infra.security.TokenService;
import com.poc.diospringboot.service.AuthenticationService;
import com.poc.diospringboot.service.FindByEmailService;
import com.poc.diospringboot.service.exception.ServiceException;
import jakarta.security.auth.message.callback.PasswordValidationCallback;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")

public class LoginUserController {
    private final AuthenticationService authenticationService;

    public LoginUserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity execute(@RequestBody final UserLoginDTO userLoginDTO) throws ServiceException, ControllerException {
            String token = authenticationService.execute(userLoginDTO.loginDTO(userLoginDTO));

            return ResponseEntity.ok(new UserResponseDTO(userLoginDTO.email(), token));
    }
}
