package com.poc.diospringboot.api.request.user;

import com.poc.diospringboot.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "E-mail deve estar em um formato válido")
    private String email;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public User toDomain(){
        return new User(name, email, login, password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
