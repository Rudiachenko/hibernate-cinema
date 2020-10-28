package com.dev.cinema.dao;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    User findById(Long id);

    Optional<User> findByEmail(String email);
}
