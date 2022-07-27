package me.spike.springbootredis;

import me.spike.springbootredis.repository.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class SpringBootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisApplication.class, args);
	}

	@Bean
	public RedisTemplate<String, UserDto> createRedisTemplate(RedisConnectionFactory connectionFactory){
		final RedisTemplate<String, UserDto> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public HashOperations<String, String, UserDto> createRedisHashOperation(
			RedisTemplate<String, UserDto> redisTemplate) {
		return redisTemplate.opsForHash();
	}

}
