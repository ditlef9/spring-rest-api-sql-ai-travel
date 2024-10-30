/*
* src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/Interest.java
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

/**
 * Entity class representing an Interest, associated with a User.
 * This class is used to store various interests a user might have.
 */
@Entity(name="interests")
public class Interest {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 5)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER) // we will not fetch user details when we fetch Interest
	@JsonIgnore
	private User user;

	// Getters amd setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Interest [id=" + id + ", description=" + description + "]";
	}

	

}
