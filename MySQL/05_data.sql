/*======================================================================================================================
 Simple insertion
 */

INSERT INTO roles (name) VALUES ('manager');
INSERT INTO roles (name) VALUES ('hr');
INSERT INTO roles (name) VALUES ('developer');
INSERT INTO employees (first_name, last_name, role_id) VALUES ('Mike', 'Hussey', 1);

/*======================================================================================================================
 INSERT with Shared primary key between two tables.
 */

INSERT INTO employees (first_name, last_name, role_id) VALUES ('Adam', 'Lee', 2);
SET @employee_id = LAST_INSERT_ID();
INSERT INTO address (id, door_no, street, area, city, state, country, pin_code)
    VALUES (@employee_id, '1234', 'Rkm street', 'Playfield', 'Anytowm', 'Anystate', 'Anycountry', '123456');

SELECT e.first_name, e.last_name, a.pin_code
FROM employees e
    JOIN address a on e.id = a.id;

/*======================================================================================================================
 SELECT query joining 3 tables
 */

SELECT e.id, e.first_name, a.pin_code, r.name as role_name
FROM employees e
    JOIN address a ON e.id = a.id
    JOIN roles r ON e.role_id = r.id;
