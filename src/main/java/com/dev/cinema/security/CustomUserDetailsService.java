package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.dev.cinema.model.User user = userService.findByEmail(email);
        User.UserBuilder builder;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            String[] roleStrings = user.getRole().stream()
                    .map(Role::getRoleName)
                    .map(String::valueOf)
                    .collect(Collectors.toList())
                    .toArray(String[]::new);
            builder.roles(roleStrings);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}