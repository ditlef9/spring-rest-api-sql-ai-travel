/*
 * src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/UserController.java
 * For H2 database
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import com.ekeberg.spring_rest_api_sql_ai_travel.exception.UserNotFoundException;
import com.ekeberg.spring_rest_api_sql_ai_travel.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    // Database repositories
    private UserRepository userRepository;
    private InterestRepository interestRepository;

    // Password encrypter
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Constructor
    public UserController(UserRepository userRepository, InterestRepository interestRepository){
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    // GET users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    // Get user
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        // Uses EntityModel to wrap in links
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }


    // Create user and return token
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody User user) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);  // Set the hashed password

        User savedUser = userRepository.save(user);

        // Generate JWT token using the user's email as the subject
        String token = JwtUtil.generateToken(savedUser.getEmail());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // Prepare response JSON with ID and token
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", savedUser.getId());
        responseBody.put("token", "Bearer " + token);

        return ResponseEntity.created(location).body(responseBody);
    }


    // Delete user with token validation
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable int id,
            @RequestHeader("Authorization") String token) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        // Extract email from token and validate against the user email
        String userEmail = user.get().getEmail();
        if (!JwtUtil.validateToken(token.replace("Bearer ", ""), userEmail)) {
            return ResponseEntity.status(403).body("Invalid or expired token");
        }

        // If validation is successful, delete the user
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    // Get users interest
    @GetMapping("/users/{id}/interests")
    public List<Interest> retrieveInterestsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getInterest();
    }

    // Create users interest
    @PostMapping("/users/{id}/interests")
    public ResponseEntity<?> createInterestForUser(@PathVariable int id, @RequestHeader("Authorization") String token, @Valid @RequestBody Interest interest) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        // Extract email from token and validate against the user email
        String userEmail = user.get().getEmail();
        if (!JwtUtil.validateToken(token.replace("Bearer ", ""), userEmail)) {
            return ResponseEntity.status(403).body("Invalid or expired token");
        }

        // Set interest for the user
        interest.setUser(user.get());
        Interest savedInterest = interestRepository.save(interest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedInterest.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
