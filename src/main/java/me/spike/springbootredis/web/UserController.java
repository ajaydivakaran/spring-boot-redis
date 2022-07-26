package me.spike.springbootredis.web;

import me.spike.springbootredis.domain.CreateUserRequest;
import me.spike.springbootredis.domain.UserService;
import me.spike.springbootredis.web.contract.UserCreatedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
