# Cheatsheets

## Start MySQL

```batch
mysql -h localhost -P 3306 -u root -p // Enter password in prompt
mysql -h localhost -P 3306 -u root -p"test123" // Password in command line
```

## Create role and assign grants

- In below example admin role created using ROOT account.
- SQL can be logged in as multiple user, the default user is ROOT.
- But Database administrator, should not use ROOT as credentials.
- Using ROOT credentials, create ADMIN role, create users and assign ADMIN role to them.
- With this admin role create further roles and users.

```sql
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
```

## Create user and assign roles

```sql
CREATE USER 'mvwaran'@'%' IDENTIFIED BY 'mvwaran123';
GRANT 'admin'@'%' TO 'mvwaran'@'%';
SET DEFAULT ROLE 'admin'@'%' TO 'mvwaran'@'%';
FLUSH PRIVILEGES;
```

## Create and Use database

```sql
CREATE DATABASE test_organization;
CREATE DATABASE test_no_access_organization; -- only admin have access, not developer, app and analyst

-- Switch between databases
USE test_organization;
```

## Create table

```sql
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
) AUTO_INCREMENT = 100000;
```

## Alter table

```sql
-- Add column with foreign key support
ALTER TABLE employees
ADD COLUMN role_id INT,
ADD FOREIGN KEY (role_id) REFERENCES roles(id);

-- Modify column, here not null is added
ALTER TABLE employees
MODIFY COLUMN role_id INT NOT NULL;

-- Drop foreign key constraint
ALTER TABLE employees DROP FOREIGN KEY employees_ibfk_1;

-- Drop column
ALTER TABLE employees DROP COLUMN role_id;
```

## Insert data

```sql
-- Simple data insertion
INSERT INTO roles (id, name) VALUES ('manager', 'Manager');

-- Shared primary key example insertion
INSERT INTO employees (first_name, last_name, role_id) VALUES ('Adam', 'Lee', 'hr');
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, door_no, street, area, city, state, country, pin_code)
    VALUES (@employee_id, '1234', 'Rkm street', 'Playfield', 'Anytowm', 'Anystate', 'Anycountry', '123456');
```

## Utils

```sql
-- Get MySQL version
SELECT VERSION();

-- Truncate table disabling foreign key checks
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE employees;
SET FOREIGN_KEY_CHECKS = 1;

-- Get create table sql code
SHOW CREATE TABLE employees;
```