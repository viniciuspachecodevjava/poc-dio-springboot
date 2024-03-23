package com.poc.diospringboot.domain.user;

public class UserBuilder {
    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;

    public UserBuilder() {

    }

    public static UserBuilder anUser() {
        UserBuilder builder = new UserBuilder();
        inicializadorDeDadosPadrao(builder);
        return builder;
    }

    private static void inicializadorDeDadosPadrao(UserBuilder userBuilder) {
        userBuilder.id = 1L;
        userBuilder.name = "Valid User";
        userBuilder.login = "Valid Login";
        userBuilder.password = "ValidPassword";
    }

    public UserBuilder withId(Long id) {
        id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        email = email;
        return this;
    }

    public UserBuilder withLogin(String login) {
        login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        password = password;
        return this;
    }

    public User build() {
        return new User(id, name, email, login, password);
    }
}
