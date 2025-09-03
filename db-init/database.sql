
CREATE DATABASE IF NOT EXISTS devsu;

USE devsu;

CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    gender CHAR(1) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    identification_type VARCHAR(5) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    password VARCHAR(200) NOT NULL,
    state BOOLEAN NOT NULL
);

CREATE TABLE accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(10) NOT NULL,
    account_number VARCHAR(50) NOT NULL UNIQUE,
    balance DECIMAL(15,2) NOT NULL,
    state BOOLEAN NOT NULL,
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE movements (
    movement_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_date DATETIME NOT NULL,
    value DECIMAL(15,2) NOT NULL,
    balance DECIMAL(15,2) NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    account_id BIGINT NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);