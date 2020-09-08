package com.internet.shop.service;

import com.internet.shop.model.User;
import java.util.List;

public interface UserService {
    User create(User user);

    User get(Long id);

    User update(User user);

    boolean delete(Long id);

    List<User> getAll();
}
