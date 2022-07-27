package me.spike.springbootredis.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.spike.springbootredis.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDbo {
    private String name;
    private String id;

    public User toModel() {
        return new User(name);
    }
}
