package com.dev.cinema.security;

import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import exceptions.AuthenticationException;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent() && isPasswordValid(password, userFromDb.get())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("You entered incorrect email or password.");
    }

    @Override
    public User register(String email, String password) {
        User user = userService.add(new User(email, password));
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean isPasswordValid(String password, User user) {
        return HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
