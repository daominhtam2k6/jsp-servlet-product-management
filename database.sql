CREATE DATABASE IF NOT EXISTS product_management;

USE product_management;

CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL
);

INSERT INTO products (name, price, quantity)
VALUES
('Laptop Dell', 1500, 10),
('iPhone 15', 1000, 20),
('Mechanical Keyboard', 80, 30);
