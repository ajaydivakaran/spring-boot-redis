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
    private final RedisTemplate<String, UserDto> redisTemplate;

    public UserRepository(RedisTemplate<String, UserDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public void save(CreateUserRequest newUser, String id) {
        redisTemplate.opsForValue().set(id, new UserDto(newUser.getName(), id));
        redisTemplate.expire(id, 30, TimeUnit.SECONDS);
    }

    public Optional<User> find(String id) {
        final UserDto userDto = redisTemplate.opsForValue().get(id);
        return userDto == null ? Optional.empty() : Optional.of(userDto.toModel());
    }
}
