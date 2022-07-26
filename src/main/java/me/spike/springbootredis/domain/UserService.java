package me.spike.springbootredis.domain;

import me.spike.springbootredis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String createUser(CreateUserRequest request) {
        final String userId = UUID.randomUUID().toString();
        repository.save(request, userId);
        return userId;
    }

    public Optional<User> fetchUser(String id) {
        return repository.find(id);
    }
}
