package com.poc.diospringboot.api.response.user;

import com.poc.diospringboot.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserResponse {
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "E-mail deve estar em um formato válido")
    private String email;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private String token;
    public User toDomain() {
        return new User(name, email, login, password);
    }

    public UserResponse(String email, String token) {
        this.email = email;
        this.token = token;
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

    public String getToken() {
        return token;
    }
}
