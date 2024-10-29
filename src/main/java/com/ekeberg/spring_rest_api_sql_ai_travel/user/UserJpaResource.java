/*
 * src/main/java/com/ekeberg/spring_rest_api_sql_ai_travel/user/UserJpaResource.java
 * For H2 database
 */
package com.ekeberg.spring_rest_api_sql_ai_travel.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;
    private InterestRepository interestRepository;
    public UserJpaResource(UserRepository userRepository, InterestRepository interestRepository){
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

        // Filter
        //MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        //SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("password");
        //FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);


        // Uses EntityModel to wrap in links
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }


    // Create user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
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
    public ResponseEntity createInterestForUser(@PathVariable int id, @Valid @RequestBody Interest interest) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        interest.setUser(user.get());
        Interest savedInterest = interestRepository.save(interest);

        // URI for interest
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedInterest.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

}
