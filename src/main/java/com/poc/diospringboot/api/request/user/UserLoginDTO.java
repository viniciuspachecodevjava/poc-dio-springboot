package com.poc.diospringboot.api.request.user;

import com.poc.diospringboot.domain.user.User;

public record UserLoginDTO(String email, String password) {
    public User loginDTO(UserLoginDTO userLoginDTO) {
        return new User(userLoginDTO.email, userLoginDTO.password);
    }
}
