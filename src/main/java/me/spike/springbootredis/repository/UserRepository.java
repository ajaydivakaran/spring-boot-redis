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
    private final HashOperations<String, String, UserDbo> redisTemplate;

    public UserRepository(HashOperations<String, String, UserDbo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public void save(CreateUserRequest newUser, String id) {
        redisTemplate.put(id, HASH_KEY, new UserDbo(newUser.getName(), id));
        redisTemplate.getOperations().expire(id, 30, TimeUnit.SECONDS);
    }

    public Optional<User> find(String id) {
        var user = redisTemplate.get(id, HASH_KEY);
        return user == null ? Optional.empty() : Optional.of(user.toModel());
    }
}
