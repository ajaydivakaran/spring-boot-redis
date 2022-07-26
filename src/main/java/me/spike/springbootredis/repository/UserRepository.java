package me.spike.springbootredis.repository;

import me.spike.springbootredis.domain.CreateUserRequest;
import me.spike.springbootredis.domain.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepository {
    public static final String HASH_KEY = "users-hk";
    private final HashOperations<String, String, UserDto> redisTemplate;

    public UserRepository(HashOperations<String, String, UserDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(CreateUserRequest newUser, String id) {
        redisTemplate.put(id, HASH_KEY, new UserDto(newUser.getName(), id));
    }

    public Optional<User> find(String id) {
        final UserDto userDto = redisTemplate.get(id, HASH_KEY);
        return userDto == null ? Optional.empty() : Optional.of(userDto.toModel());
    }
}
