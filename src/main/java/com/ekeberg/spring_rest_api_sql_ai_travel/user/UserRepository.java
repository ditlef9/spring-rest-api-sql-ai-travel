/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/UserRepository.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity, extending JpaRepository.
 * Provides database access methods for User data.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
