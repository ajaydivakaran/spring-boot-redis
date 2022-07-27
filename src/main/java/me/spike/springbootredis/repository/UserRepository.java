package me.spike.springbootredis.repository;

import me.spike.springbootredis.domain.CreateUserRequest;
import me.spike.springbootredis.domain.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserRepository {
    private static final String HASH_KEY = "user";
    private final HashOperations<String, String, UserDto> redisTemplate;

    public UserRepository(HashOperations<String, String, UserDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public void save(CreateUserRequest newUser, String id) {
        redisTemplate.put(id, HASH_KEY, new UserDto(newUser.getName(), id));
        redisTemplate.getOperations().expire(id, 10, TimeUnit.SECONDS);
    }

    public Optional<User> find(String id) {
        final UserDto userDto = redisTemplate.get(id, HASH_KEY);
        return userDto == null ? Optional.empty() : Optional.of(userDto.toModel());
    }
}
