package com.poc.diospringboot.gateway.database.model;

import com.poc.diospringboot.domain.user.User;
import jakarta.persistence.*;

@Entity
public class UserDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    public UserDatabase(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
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
