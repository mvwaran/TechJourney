# Root have all access

# Admin management
CREATE USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY 'admin123';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%'; # For tables
GRANT CREATE USER, GRANT OPTION ON *.* TO 'admin'@'%'; # Admin can create user similar to root
FLUSH PRIVILEGES;
DROP USER 'admin'@'%';

# Developer management, developer will all access for tables
CREATE USER 'developer'@'%' IDENTIFIED WITH mysql_native_password BY 'developer123';
GRANT ALL PRIVILEGES ON test_company.* TO 'developer'@'%';
FLUSH PRIVILEGES;
DROP USER 'developer'@'%';

# Tester management, tester will have only read access for tables
CREATE USER 'tester'@'%' IDENTIFIED WITH mysql_native_password BY 'tester123';
GRANT SELECT ON test_company.* TO 'tester'@'%';
FLUSH PRIVILEGES;
DROP USER 'tester'@'%';

# Manager management, manager will have all access except delete
CREATE USER 'manager'@'%' IDENTIFIED WITH mysql_native_password BY 'manager123';
GRANT ALL PRIVILEGES ON test_company.* TO 'manager'@'%';
REVOKE DELETE ON test_company.* FROM 'manager'@'%';
FLUSH PRIVILEGES;
DROP USER 'manager'@'%';