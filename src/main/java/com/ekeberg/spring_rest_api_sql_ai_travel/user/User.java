/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/User.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;


/**
 * Entity class representing a User in the system.
 * Stores user information including name, email, birth date, password, and interests.
 */
// @JsonFilter("UserFilter")
@Entity(name="users")
public class User {

    protected User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for H2
    private Integer id;

    @Size(min=2, message = "Name should have at least two characters")
    @JsonProperty("name")
    private String name;

    @Size(min=2, message = "Email should have at least two characters")
    @JsonProperty("email")
    private String email;

    @Past(message = "Birth Date should be in the past")
    private LocalDate birthDate;

    @Size(min=2, message = "Password should have at least two characters")
    private String password;

    // Interest (A user may have many interests, for example beach, sun, sightseeing, jungle)
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Interest> interest;

    // Constructor
    public User(Integer id, String name, String email, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Interest> getInterest() {
        return interest;
    }

    public void setInterest(List<Interest> interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", interest=" + interest +
                '}';
    }
}
