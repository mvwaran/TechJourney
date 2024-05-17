# Create admin role first with all grant, ROOT should only be used one time for creating admin
# Subsequent action should only be done by admin or other roles/users, not root
CREATE ROLE 'admin'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
GRANT CREATE USER, CREATE ROLE, GRANT OPTION ON *.* TO 'admin'@'%'; # Above grant is not enough for creating role and user
FLUSH PRIVILEGES;
# DROP ROLE 'admin'@'%';

# Create an user and assign admin role to him
CREATE USER 'mvwaran'@'%' IDENTIFIED BY 'mvwaran123';
GRANT 'admin'@'%' TO 'mvwaran'@'%';
SET DEFAULT ROLE 'admin'@'%' TO 'mvwaran'@'%';
FLUSH PRIVILEGES;

# Sign in as mvwaran and start creating further roles
CREATE ROLE 'developer';
GRANT ALL PRIVILEGES ON test_company.* TO 'developer'@'%';
CREATE ROLE 'app';
GRANT ALL PRIVILEGES ON test_company.* TO 'app'@'%';
CREATE ROLE 'analyst';
GRANT SELECT ON test_company.* TO 'analyst'@'%'; # analyst has only read access
# REVOKE DELETE ON test_company.* FROM 'manager'@'%';
FLUSH PRIVILEGES;

# Create further users / app
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