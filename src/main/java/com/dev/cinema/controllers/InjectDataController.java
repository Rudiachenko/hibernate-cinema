package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectDataController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InjectDataController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String injectRolesIntoDb() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);
        return "Roles injected successfully ";
    }

    @GetMapping("/users")
    public String injectUsersIntoDb() {
        Role userRole = roleService.getRoleByName(Role.RoleName.USER);
        Role userAdmin = roleService.getRoleByName(Role.RoleName.ADMIN);
        User taras = new User("taras@gmail.com", "password", Set.of(userRole));
        userService.add(taras);
        User andrew = new User("andrew@gmail.com", "password", Set.of(userRole));
        userService.add(andrew);
        User admin = new User("fedia@gmail.com", "password", Set.of(userAdmin));
        userService.add(admin);
        return "Users injected successfully ";
    }
}
