-- This is a playground to execute SQL scripts in README.md file

# SET FOREIGN_KEY_CHECKS = 0;
# DROP USER admin;
# DROP USER analyst;
# DROP USER app;
# DROP USER developer;
# DROP USER karikalan;
# DROP USER kumar;
# DROP USER karuvaki;
# DROP USER mvwaran;
# DROP USER service_account;
# DROP TABLE employees;
# DROP TABLE roles;
# DROP TABLE address;
# SET FOREIGN_KEY_CHECKS = 1;

DROP DATABASE test_organization;
# DROP DATABASE test_no_access_organization;

-- Create admin role and grant access to whole
CREATE ROLE 'admin'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
GRANT CREATE USER, CREATE ROLE, GRANT OPTION ON *.* TO 'admin'@'%'; -- Above grant is not enough for creating role and user
FLUSH PRIVILEGES;

-- Create other supporting roles and grant access to only test_organization database
CREATE ROLE 'developer';
GRANT ALL PRIVILEGES ON test_organization.* TO 'developer'@'%';
CREATE ROLE 'app';
GRANT ALL PRIVILEGES ON test_organization.* TO 'app'@'%';
CREATE ROLE 'analyst';
GRANT SELECT ON test_organization.* TO 'analyst'@'%';
FLUSH PRIVILEGES;

-- Create user and assign admin role
CREATE USER 'mvwaran'@'%' IDENTIFIED BY 'mvwaran123';
GRANT 'admin'@'%' TO 'mvwaran'@'%';
SET DEFAULT ROLE 'admin'@'%' TO 'mvwaran'@'%';
FLUSH PRIVILEGES;

-- Create users in your organization / apps in your organization and set role accordingly.
CREATE USER 'karikalan'@'%' IDENTIFIED BY 'karikalan123';
GRANT 'developer'@'%' TO 'karikalan'@'%';
SET DEFAULT ROLE 'developer'@'%' TO 'karikalan'@'%';
CREATE USER 'karuvaki'@'%' IDENTIFIED BY 'karuvaki123';
GRANT 'analyst'@'%' TO 'karuvaki'@'%';
SET DEFAULT ROLE 'analyst'@'%' TO 'karuvaki'@'%';
CREATE USER 'service_account'@'%' IDENTIFIED BY 'service_account123'; # This is a service account not user
GRANT 'app'@'%' TO 'service_account'@'%';
SET DEFAULT ROLE 'app'@'%' TO 'service_account'@'%';
FLUSH PRIVILEGES;

-- Create databases
CREATE DATABASE test_organization;

USE test_organization;

-- Create tables
CREATE TABLE roles (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    role_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
) AUTO_INCREMENT = 100000;

CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(50) NOT NULL,
    pin_code VARCHAR(6) NOT NULL,
    FOREIGN KEY (id) REFERENCES employees(id)
);

CREATE TABLE assets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(10) NOT NULL,
    emp_id INT NOT NULL,
    FOREIGN KEY (emp_id) REFERENCES employees(id)
);

-- Data insertion
INSERT INTO roles (id, name) VALUES ('manager', 'Manager');
INSERT INTO roles (id, name) VALUES ('hr', 'Human Resources');
INSERT INTO roles (id, name) VALUES ('developer', 'Developer');

-- $2a$10$3N2ZjQ7AFhIyJWLYcIq4O.A9B4LqCsPfv1239YA/RvSCxTxkfaGm2 decodes to neduncheliyan123
INSERT INTO employees (password, name, role_id) VALUES ('$2a$10$3N2ZjQ7AFhIyJWLYcIq4O.A9B4LqCsPfv1239YA/RvSCxTxkfaGm2', 'Neduncheliyan', 'hr');
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, area, pin_code) VALUES (@employee_id, 'Bodi', '123456');
INSERT INTO assets(name, category, emp_id) VALUES ('HP Pavilion 15', 'LAPTOP', @employee_id);
INSERT INTO assets(name, category, emp_id) VALUES ('HP Pavilion Keyboard 1', 'KEYBOARD', @employee_id);

-- $2a$10$Fh8zInGfx5QJL5dPlOk0heLsr.xQYmRyQ7wYRmsxDyLI0Ass1assC decodes to karikalan123
INSERT INTO employees (password, name, role_id) VALUES ('$2a$10$Fh8zInGfx5QJL5dPlOk0heLsr.xQYmRyQ7wYRmsxDyLI0Ass1assC', 'karikalan', 'manager');
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, area, pin_code) VALUES (@employee_id, 'Theni', '453567');
INSERT INTO assets(name, category, emp_id) VALUES ('Dell Mouse 13', 'MOUSE', @employee_id);
