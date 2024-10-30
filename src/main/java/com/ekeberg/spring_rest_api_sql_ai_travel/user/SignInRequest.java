/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/SignInRequest.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

/**
 * DTO class for handling user sign-in requests.
 * Stores user credentials: email and password.
 * The SignInRequest class serves as a Data Transfer Object (DTO) specifically for handling user sign-in requests.
 * The class is named SignInRequest instead of SignInController because it represents data for a request
 * rather than the logic or operations to handle the request.
 */
public class SignInRequest {
    private String email;
    private String password;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
