package com.internet.shop.dao;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(u -> u.getId().equals(id))
                .map(User::clone)
                .findFirst();
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(i -> Storage.users.get(i).getId().equals(user.getId()))
                .mapToObj(i -> Storage.users.set(i, user))
                .findFirst()
                .orElseThrow();
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(u -> u.getId().equals(id));
    }

    @Override
    public List<User> getAll() {
        return Storage.users.stream()
                .map(User::clone)
                .collect(Collectors.toList());
    }
}
