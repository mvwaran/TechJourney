-- This is a playground to execute SQL scripts in README.md file

# SET FOREIGN_KEY_CHECKS = 0;
# DROP USER admin;
# DROP USER analyst;
# DROP USER app;
# DROP USER developer;
# DROP USER john;
# DROP USER kumar;
# DROP USER lisa;
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
CREATE USER 'john'@'%' IDENTIFIED BY 'john123';
GRANT 'developer'@'%' TO 'john'@'%';
SET DEFAULT ROLE 'developer'@'%' TO 'john'@'%';
CREATE USER 'lisa'@'%' IDENTIFIED BY 'lisa123';
GRANT 'analyst'@'%' TO 'lisa'@'%';
SET DEFAULT ROLE 'analyst'@'%' TO 'lisa'@'%';
CREATE USER 'service_account'@'%' IDENTIFIED BY 'service_account123'; # This is a service account not user
GRANT 'app'@'%' TO 'service_account'@'%';
SET DEFAULT ROLE 'app'@'%' TO 'service_account'@'%';
FLUSH PRIVILEGES;

-- Create databases
CREATE DATABASE test_organization;
CREATE DATABASE test_no_access_organization;

-- Create tables
CREATE TABLE roles (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(50) NOT NULL,
    pin_code VARCHAR(6) NOT NULL,
    FOREIGN KEY (id) REFERENCES employees(id)
);

-- Data insertion
INSERT INTO roles (id, name) VALUES ('manager', 'Manager');
INSERT INTO roles (id, name) VALUES ('hr', 'Human Resources');
INSERT INTO roles (id, name) VALUES ('developer', 'Developer');

INSERT INTO employees (first_name, last_name, role_id) VALUES ('Adam', 'Lee', 'hr');
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, area, pin_code) VALUES (@employee_id, 'Bodi', '123456');

INSERT INTO employees (first_name, last_name, role_id) VALUES ('John', 'Lee', 'manager');
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, area, pin_code) VALUES (@employee_id, 'Theni', '453567');
