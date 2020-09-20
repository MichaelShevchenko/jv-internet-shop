package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.exceptions.DataBaseException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product item) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }
            return item;
        } catch (SQLException message) {
            throw new DataBaseException("Couldn't create provided product: " + item, message);
        }
    }

    @Override
    public Optional<Product> get(Long itemId) {
        String query = "SELECT * FROM products WHERE id = ? AND deleted = 0";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(composeExtractedProduct(resultSet));
            }
            return Optional.empty();
        } catch (SQLException message) {
            throw new DataBaseException("Couldn't get product by specified id: " + itemId, message);
        }
    }

    @Override
    public Product update(Product item) {
        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException message) {
            throw new DataBaseException("Couldn't update provided product: " + item, message);
        }
    }

    @Override
    public boolean deleteById(Long itemId) {
        String query = "UPDATE products SET deleted = 1 WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, itemId);
            return statement.executeUpdate() == 1;
        } catch (SQLException message) {
            throw new DataBaseException("Couldn't delete product by specified id: "
                                                                + itemId, message);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> allProducts = new ArrayList<>();
        String query = "SELECT * FROM products WHERE deleted = 0";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allProducts.add(composeExtractedProduct(resultSet));
            }
            return allProducts;
        } catch (SQLException message) {
            throw new DataBaseException("Couldn't get products from DataBase", message);
        }
    }

    private Product composeExtractedProduct(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Product newProduct = new Product(name, price);
        newProduct.setId(id);
        return newProduct;
    }
}
