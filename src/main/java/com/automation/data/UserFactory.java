package com.automation.data;

import java.util.UUID;

public class UserFactory {

    private static final String DEFAULT_PASSWORD = "secret_sauce";

    public static UserCredentials standard() {
        return new UserCredentials("standard_user", DEFAULT_PASSWORD, "user");
    }

    public static UserCredentials locked() {
        return new UserCredentials("locked_out_user", DEFAULT_PASSWORD, "user");
    }

    public static UserCredentials problem() {
        return new UserCredentials("problem_user", DEFAULT_PASSWORD, "user");
    }

    public static UserCredentials slow() {
        return new UserCredentials("performance_glitch_user", DEFAULT_PASSWORD, "user");
    }

    public static UserCredentials invalid() {
        return new UserCredentials("invalid_user_" + UUID.randomUUID().toString().substring(0, 8), "wrong_password", "none");
    }
}
