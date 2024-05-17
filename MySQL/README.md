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
GRANT CREATE USER, CREATE ROLE, GRANT OPTION ON *.* TO 'admin'@'%'; # Above grant is not enough for creating role and user
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

- Role table is straight forwarded.
```sql
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
```

## Other

- Utilities command in `01_utils.sql`
- Create ROLES and USERS in `02_roles_and_users.sql`
   - Using ROOT, create ADMIN role
   - Using ROOT, create user and assign admin role
   - With Admin user, create additional roles and users
   - Assign roles to user
- Database commands in `03_database.sql`
- Tables in `04_tables.sql`
   - CREATE simple table
   - ALTER table by introducing new column with foreign key constraint
   - CREATE table but with Shared primary key.
   - DROP table
- CRUD queries in `05_data.sql`
  - Simple INSERT statement
  - Shared primary key between two tables `Address` and `Employees`.
    - INSERT query with shared primary key 
    - SELECT query joining 2 tables with Shared primary key
  - SELECT query joining 3 tables
