/*
 * src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/UserNotFoundException.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
