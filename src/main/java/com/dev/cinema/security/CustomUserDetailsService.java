package com.dev.cinema.security;

import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        User.UserBuilder builder = org.springframework.security.core
                .userdetails
                .User.withUsername(email);
        builder.password(user.getPassword());
        String[] roleStrings = user.getRole().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toList())
                .toArray(String[]::new);
        builder.roles(roleStrings);
        return builder.build();
    }
}
