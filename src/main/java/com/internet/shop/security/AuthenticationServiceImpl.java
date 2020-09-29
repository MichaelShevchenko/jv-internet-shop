package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import com.internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByLogin(login);
        if (userFromDB.isPresent() && isValidPassword(password, userFromDB.get())) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    private boolean isValidPassword(String password, User userFromDbPassword) {
        return HashUtil.hashPassword(password, userFromDbPassword.getSalt())
                .equals(userFromDbPassword.getPassword());
    }
}
