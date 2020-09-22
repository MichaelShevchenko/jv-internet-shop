package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ? AND deleted = FALSE";
        Optional<User> searchedUser = Optional.empty();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                searchedUser = Optional.of(composeExtractedUser(resultSet));
            }
            if (searchedUser.isEmpty()) {
                return searchedUser;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't find user by specified login: " + login, e);
        }
        searchedUser.get().setRoles(composeRoleSetByUserId(searchedUser.get().getId()));
        return searchedUser;
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (user_name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create provided user: " + user, e);
        }
        setRolesIdAndAddRoles(user.getRoles(), user.getId());
        return user;
    }

    @Override
    public Optional<User> get(Long userId) {
        /*String query = "SELECT user_id, user_name, login, password, role_id, role_name FROM users "
                + "INNER JOIN users_roles USING (user_id) INNER JOIN roles USING (role_id) "
                + "WHERE user_id = ? AND users.deleted = FALSE";*/
        String query = "SELECT * FROM users WHERE user_id = ? AND users.deleted = FALSE";
        Set<Role> userRoleSet = composeRoleSetByUserId(userId);
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User foundUser = composeExtractedUser(resultSet);
                foundUser.setRoles(userRoleSet);
                return Optional.of(foundUser);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't find user by specified ID:  " + userId, e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET user_name = ?, login = ?, password = ? "
                + "WHERE user_id = ? AND deleted = FALSE";
        deleteRoles(user.getId());
        setRolesIdAndAddRoles(user.getRoles(), user.getId());
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update provided user: " + user, e);
        }
    }

    @Override
    public boolean deleteById(Long userId) {
        String query = "UPDATE users SET deleted = TRUE WHERE user_id = ? AND deleted = FALSE";
        deleteRoles(userId);
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete product by specified id: "
                    + userId, e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        String query = "SELECT * FROM users WHERE deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allUsers.add(composeExtractedUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get users from DataBase", e);
        }
        for (User user : allUsers) {
            user.setRoles(composeRoleSetByUserId(user.getId()));
        }
        return allUsers;
    }

    private User composeExtractedUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("user_id");
        String name = resultSet.getString("user_name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        User newUser = new User(name, login, password);
        newUser.setId(id);
        return newUser;
    }

    private Set<Role> composeRoleSetByUserId(long userId) {
        Set<Role> userRoles = new HashSet<>();
        String query = "SELECT role_id, role_name FROM users_roles JOIN roles "
                + "USING (role_id) WHERE user_id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                Role newRole = Role.of(roleName);
                newRole.setId(id);
                userRoles.add(newRole);
            }
            return userRoles;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get Roles from DataBase by specified id:"
                                                                                    + userId, e);
        }
    }

    private void setRolesIdAndAddRoles(Set<Role> roleSet, long userId) {
        String query = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, (SELECT role_id FROM roles WHERE role_name = ? AND deleted = FALSE))";
        for (Role role : roleSet) {
            try (Connection connection = ConnectionUtil.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, userId);
                statement.setString(2, role.getRoleName().toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Couldn't add roles for provided userID: "
                                                                                + userId, e);
            }
        }
    }

    private void deleteRoles(long userId) {
        String query = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete roles for provided userID: " + userId, e);
        }
    }

    /*private void addRoles(Set<Role> userRoles, long userId) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            for (Role role : userRoles) {
                statement.setLong(1, userId);
                statement.setLong(2, role.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't add roles for provided userID: " + userId, e);
        }
    }*/

    /*private Set<Role> setRolesId(Set<Role> roleSet) {
        Set<Role> userRoles = new HashSet<>();
        String query = "SELECT * FROM roles WHERE deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                Role newRole = Role.of(roleName);
                newRole.setId(id);
                for (Role role : roleSet) {
                    if (role.getRoleName().equals(newRole.getRoleName())) {
                        userRoles.add(newRole);
                    }
                }
            }
            return userRoles;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get users from DataBase", e);
        }
    }*/
}
