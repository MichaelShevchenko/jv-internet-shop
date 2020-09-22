package com.internet.shop;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.dao.UserDao;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import java.util.Set;

public class Application {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductDao productDaoJdbc = (ProductDao) injector.getInstance(ProductDao.class);
        UserDao userDaoJdbc = (UserDao) injector.getInstance(UserDao.class);
        System.out.println(productDaoJdbc.getAll());
        User toTest = userDaoJdbc.get(13L).get();
        System.out.println(toTest);
        toTest.setRoles(Set.of(Role.of("USER")));
        Set<Role> res = toTest.getRoles();
        for (Role role : res) {
            if (role.getRoleName().equals(Role.RoleName.ADMIN)) {
                role.setId(1L);
            }
            if (role.getRoleName().equals(Role.RoleName.USER)) {
                role.setId(2L);
            }
        }
        userDaoJdbc.update(toTest);
        System.out.println(userDaoJdbc.get(13L).get());
        System.out.println(userDaoJdbc.findByLogin("Diagram"));
        System.out.println(userDaoJdbc.getAll());
    }
}
