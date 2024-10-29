/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/UserRepository.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository to talk to H2 database
// Can be moved to jpa package
public interface UserRepository extends JpaRepository<User, Integer> {

}
