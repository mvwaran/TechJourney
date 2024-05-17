# Get user
SELECT USER();

# Get mysql version
SELECT VERSION();

# Truncate table irrespective of foreign key check
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE address;
TRUNCATE TABLE employees;
TRUNCATE TABLE roles;
SET FOREIGN_KEY_CHECKS = 1;