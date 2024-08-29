package com.poc.diospringboot.api.response.user;

public record UserResponseDTO(String email, String token) {
    public UserResponseDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
