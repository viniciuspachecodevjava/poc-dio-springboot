package com.poc.diospringboot.api.controller;

import com.poc.diospringboot.api.request.user.UserRequest;
import com.poc.diospringboot.service.CreateUserService;
import com.poc.diospringboot.service.exception.ServiceException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateUserController {
    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping(value = "/newuser")
    @ResponseStatus(code = HttpStatus.OK)
    public void execute(@RequestBody @Valid UserRequest userRequest) throws ServiceException {
        createUserService.execute(userRequest.toDomain());
    }
}
