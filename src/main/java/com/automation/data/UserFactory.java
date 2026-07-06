package com.automation.data;

/**
 * UserFactory — Static factory methods for common user personas.
 * Delegates to UserBuilder (Law 6, ADR-008).
 */
public class UserFactory {

    private UserFactory() {}

    public static UserCredentials standard() {
        return UserBuilder.aUser().standard().build();
    }

    public static UserCredentials locked() {
        return UserBuilder.aUser().locked().build();
    }

    public static UserCredentials problem() {
        return UserBuilder.aUser().withUsername("problem_user").withPassword("secret_sauce").build();
    }

    public static UserCredentials slow() {
        return UserBuilder.aUser().withUsername("performance_glitch_user").withPassword("secret_sauce").build();
    }

    public static UserCredentials invalid() {
        return UserBuilder.aUser().invalid().build();
    }
}
