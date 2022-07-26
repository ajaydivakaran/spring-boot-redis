package me.spike.springbootredis.domain;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    public String createUser(CreateUserRequest request) {
        System.out.println(request);
        return UUID.randomUUID().toString();
    }
}
