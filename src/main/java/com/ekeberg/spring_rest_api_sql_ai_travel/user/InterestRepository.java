/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/InterestRepository.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Interest entity, extending JpaRepository.
 * Provides database access methods for Interest data.
 */
public interface InterestRepository extends JpaRepository<Interest, Integer> {

}
