package me.spike.springbootredis.repository;

import me.spike.springbootredis.domain.CreateUserRequest;
import me.spike.springbootredis.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserRepository {
    private final RedisTemplate<String, UserDbo> redisTemplate;

    public UserRepository(RedisTemplate<String, UserDbo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public void save(CreateUserRequest newUser, String id) {
        redisTemplate.opsForValue().set(id, new UserDbo(newUser.getName(), id));
        redisTemplate.expire(id, 30, TimeUnit.SECONDS);
    }

    public Optional<User> find(String id) {
        var user = redisTemplate.opsForValue().get(id);
        return user == null ? Optional.empty() : Optional.of(user.toModel());
    }
}
