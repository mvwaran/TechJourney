# Create roles table
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

#-----------------------------------------------------------------------------------------------------------------------

# Create table employees with only basic details
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

# Update employees table later with role information column
ALTER TABLE employees
ADD COLUMN role_id INT,
ADD FOREIGN KEY (role_id) REFERENCES roles(id);

#----------------------------------------------------------------------------------------------------------------------

# Create address table
# Below table uses shared primary key where address.id is employees.id
CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    door_no VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    area VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    pin_code VARCHAR(6) NOT NULL,
    FOREIGN KEY (id) REFERENCES employees(id)
);

#-----------------------------------------------------------------------------------------------------------------------

# DROP TABLE address;