package com.productmanagement.dao;

import com.productmanagement.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final String jdbcURL =
            "jdbc:mysql://localhost:3306/product_management";

    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    private Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    jdbcURL,
                    jdbcUsername,
                    jdbcPassword
            );

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public List<Product> selectAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String name =
                        resultSet.getString("name");

                double price =
                        resultSet.getDouble("price");

                int quantity =
                        resultSet.getInt("quantity");

                Product product =
                        new Product(
                                id,
                                name,
                                price,
                                quantity
                        );

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public Product selectProduct(int id) {

        Product product = null;

        String sql =
                "SELECT * FROM products WHERE id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                String name =
                        resultSet.getString("name");

                double price =
                        resultSet.getDouble("price");

                int quantity =
                        resultSet.getInt("quantity");

                product =
                        new Product(
                                id,
                                name,
                                price,
                                quantity
                        );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public void insertProduct(Product product) {

        String sql =
                "INSERT INTO products(name, price, quantity) " +
                "VALUES (?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {

        String sql =
                "UPDATE products " +
                "SET name = ?, price = ?, quantity = ? " +
                "WHERE id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {

        String sql =
                "DELETE FROM products WHERE id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
