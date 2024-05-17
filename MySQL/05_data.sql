# Simple insertion
INSERT INTO employees (first_name, last_name) VALUES ('Mike', 'Hussey');

INSERT INTO roles (name) VALUES ('manager');
INSERT INTO roles (name) VALUES ('hr');
INSERT INTO roles (name) VALUES ('developer');

#-----------------------------------------------------------------------------------------------------------------------

# Insertion with shared primary key (address and employees table share primary key)
INSERT INTO employees (first_name, last_name) VALUES ('Adam', 'Lee');
SET @employee_id = LAST_INSERT_ID();
INSERT INTO address (id, door_no, street, area, city, state, country, pin_code)
    VALUES (@employee_id, '1234', 'Rkm street', 'Playfield', 'Anytowm', 'Anystate', 'Anycountry', '123456');

SELECT e.first_name, e.last_name, a.pin_code FROM employees e JOIN address a on e.id = a.id;

#-----------------------------------------------------------------------------------------------------------------------
