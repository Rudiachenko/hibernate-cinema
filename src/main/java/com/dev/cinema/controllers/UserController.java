package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto get(@RequestParam(name="email") String email) {
        return userService.findByEmail(email)
                .map(userMapper::toUserResponseDto)
                .orElseThrow();
    }
}
