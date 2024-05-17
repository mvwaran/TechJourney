# MySQL

## Topics covered

### MySQL connection

#### Install MySQL.

- Install MySQL of your choice.

#### MySQL start commands

```batch
mysql -h localhost -P 3306 -u root -p // Enter password in prompt
mysql -h localhost -P 3306 -u root -p"test123" // Password in command line
```

### ADMIN vs ROOT

- SQL can be logged in as multiple user, the default user is ROOT.
- But Database administrator, should not use ROOT as credentials.
- Using ROOT credentials, create ADMIN role, create users and assign ADMIN role to them.
```sql
-- CREATE admin role and delegate privileges
CREATE ROLE 'admin'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
GRANT CREATE USER, CREATE ROLE, GRANT OPTION ON *.* TO 'admin'@'%'; -- Above grant is not enough for creating role and user
FLUSH PRIVILEGES;

-- CREATE user and assign admin role to them, dont forget to set admin as default role
CREATE USER 'mvwaran'@'%' IDENTIFIED BY 'mvwaran123';
GRANT 'admin'@'%' TO 'mvwaran'@'%';
SET DEFAULT ROLE 'admin'@'%' TO 'mvwaran'@'%';
FLUSH PRIVILEGES;
```
- Now stop using ROOT account. Going forward any DB administration operation should be done by ADMIN.
- Create more roles like developer (both read & write access), analyst (only read access), app (full access and used in applications)
```sql
CREATE ROLE 'developer';
GRANT ALL PRIVILEGES ON test_company.* TO 'developer'@'%';
CREATE ROLE 'app';
GRANT ALL PRIVILEGES ON test_company.* TO 'app'@'%';
CREATE ROLE 'analyst';
GRANT SELECT ON test_company.* TO 'analyst'@'%';
FLUSH PRIVILEGES;
```
- To revoke any operation to a role
```sql
REVOKE DELETE ON test_company.* FROM 'manager'@'%';
```
- Create users in your organization / apps in your organization and set role accordingly.
```sql
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
```

### CRUD Database

#### Create database

```sql
CREATE DATABASE test_company;
CREATE DATABASE test_no_access_company; -- only admin have access, not developer, app and analyst
```

#### Switch between databases

```sql
USE test_company;
```

#### Delete database

```sql
DROP DATABASE test_company;
DROP DATABASE test_no_access_company;
```

### CRUD Table

- Let's create table with simple structure / with foreign key constraint etc.
```sql

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Shared primary key, this key is referenced to employees primary key using below foreign key constraint
    door_no VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    area VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    pin_code VARCHAR(6) NOT NULL,
    FOREIGN KEY (id) REFERENCES employees(id) -- Shared primary key
);
```
- Only address table is tagged to employees table, roles table is missing right ? Let's alter the table to link roles and employees table.
```sql
-- Update employees table with role information column
ALTER TABLE employees
ADD COLUMN role_id INT, -- ADD syntax
ADD FOREIGN KEY (role_id) REFERENCES roles(id);

-- Modify the column to have NOT NULL constraint for role_id
ALTER TABLE employees
MODIFY COLUMN role_id INT NOT NULL; -- MODIFY syntax
```

### INSERT queries

- Simple insertion, Role table is simple, so lets focus on that first.
```sql
INSERT INTO roles (name) VALUES ('manager');
INSERT INTO roles (name) VALUES ('hr');
INSERT INTO roles (name) VALUES ('developer');
```
- Unlike roles table, address table data should not be inserted directly, instead while updating employee table, update address table as well as they are sharing primary key.
```sql
INSERT INTO employees (first_name, last_name, role_id) VALUES ('Adam', 'Lee', 2); -- Assigned HR role
SET @employee_id = LAST_INSERT_ID(); -- Shared primary key, so use employee table primary key in address table
INSERT INTO address (id, door_no, street, area, city, state, country, pin_code)
    VALUES (@employee_id, '1234', 'Rkm street', 'Playfield', 'Anytowm', 'Anystate', 'Anycountry', '123456');
```
- More insert queries.
```sql
INSERT INTO employees (first_name, last_name, role_id) VALUES ('Mike', 'Hussey', 1); -- Assigned Manager role
```

### SELECT queries

- Combine data from address and employees table (which shares primary key) with input as employee id.
```sql
SELECT e.first_name, e.last_name, a.pin_code
FROM employees e
    JOIN address a on e.id = a.id;
```
- Combine data from address, roles, employees with employee id as input.
```sql
SELECT e.id, e.first_name, a.pin_code, r.name as role_name
FROM employees e
    JOIN address a ON e.id = a.id
    JOIN roles r ON e.role_id = r.id;
```
