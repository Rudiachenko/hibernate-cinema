package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Role role = new Role();
        role.setRoleName(Role.RoleName.ROLE_USER);
        User user = new User();
        user.setRole(Set.of(role));
        String[] strings = user.getRole().stream()
                .map(Role::getRoleName)
                .map(String::valueOf)
                .collect(Collectors.toList())
                .toArray(String[]::new);

        System.out.println(Arrays.toString(strings));
    }
}
