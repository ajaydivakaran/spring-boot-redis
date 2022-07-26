package me.spike.springbootredis.web;

import me.spike.springbootredis.domain.CreateUserRequest;
import me.spike.springbootredis.domain.User;
import me.spike.springbootredis.domain.UserService;
import me.spike.springbootredis.web.contract.UserCreatedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public UserCreatedResponse createUser(@RequestBody CreateUserRequest request) {
        final String id = service.createUser(request);
        return new UserCreatedResponse(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") String id) {
        var maybeUser = service.fetchUser(id);
        return ResponseEntity.of(maybeUser);
    }

}
