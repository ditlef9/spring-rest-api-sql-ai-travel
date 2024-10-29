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

// User Bean
// @JsonFilter("UserFilter")
@Entity(name="user_details")
public class User {

    protected User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for H2
    private Integer id;

    @Size(min=2, message = "Name should have at least two characters")
    @JsonProperty("name")
    private String name;

    @Past(message = "Birth Date should be in the past")
    private LocalDate birthDate;

    @Size(min=2, message = "Password should have at least two characters")
    private String password;

    // Interest (A user may have many interests, for example beach, sun, sightseeing, jungle)
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Interest> interest;

    // Constructor
    public User(Integer id, String name, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
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
                ", birthDate=" + birthDate +
                '}';
    }
}
