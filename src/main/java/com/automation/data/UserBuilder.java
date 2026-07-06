package com.automation.data;

import net.datafaker.Faker;
import java.util.UUID;

/**
 * UserBuilder — Fluent API for generating test users (Law 6, ADR-008).
 * Uses Datafaker for dynamic data generation.
 */
public class UserBuilder {
    private String username;
    private String password;
    private String role;
    private final Faker faker = new Faker();

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder standard() {
        this.username = "standard_user";
        this.password = "secret_sauce";
        this.role = "user";
        return this;
    }

    public UserBuilder locked() {
        this.username = "locked_out_user";
        this.password = "secret_sauce";
        this.role = "user";
        return this;
    }

    public UserBuilder invalid() {
        this.username = faker.internet().username();
        this.password = faker.internet().password(8, 16, true, true, true);
        this.role = "none";
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserCredentials build() {
        return new UserCredentials(
            username != null ? username : faker.internet().username(),
            password != null ? password : "password123",
            role != null ? role : "user"
        );
    }
}
