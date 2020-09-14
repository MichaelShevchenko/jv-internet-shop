package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(()
                -> new AuthenticationException("Incorrect username or password"));
        if (userFromDB.getPassword().equals(password)) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
