package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @GetMapping("/inject")
    public String injectUsersIntoDb() {
        Role userRole = new Role();
        Role adminRole = new Role();
        roleService.add(userRole);
        roleService.add(adminRole);
        User taras = userService.add(new User("taras@gmail.com", "password"));
        taras.setRole(Set.of(userRole));
        User andrew = userService.add(new User("andrew@gmail.com", "password"));
        andrew.setRole(Set.of(userRole));
        User svetlana = userService.add(new User("sveta@gmail.com", "password"));
        svetlana.setRole(Set.of(userRole));
        User admin = userService.add(new User("fedia@gmail.com", "password"));
        admin.setRole(Set.of(adminRole));
        return "Users injected successfully ";
    }

    @GetMapping("/by-email")
    public UserResponseDto get(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return userMapper.toUserResponseDto(user);
    }
}
