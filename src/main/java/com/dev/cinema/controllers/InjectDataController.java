package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
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
    private final AuthenticationService authenticationService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectDataController(UserService userService,
                                RoleService roleService,
                                AuthenticationService authenticationService,
                                ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/roles")
    public String injectRolesIntoDb() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);
        return "Roles injected successfully";
    }

    @GetMapping("/users")
    public String injectUsersIntoDb() {
        Role userAdmin = roleService.getRoleByName(Role.RoleName.ADMIN);
        authenticationService.register("taras@gmail.com", "password");
        authenticationService.register("andrew@gmail.com", "password");
        User admin = new User("fedia@gmail.com", "password", Set.of(userAdmin));
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
        return "Users injected successfully";
    }
}
