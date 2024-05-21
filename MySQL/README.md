# MySQL Journey

## Problem statement

The goal is to manage employees for an IT organization.

## Database management

This was categorized using the following roles:

- **admin**: 
  - Has high-level access to the entire MySQL account, serving as an alternative to `root`.
  - Manages users.
- **developer**:
  - Has high-level access only to the `test_organization` database.
  - Manages tables.
- **analyst**:
  - Has read-only access to the `test_organization` database.
  - Accesses data.
- **app**:
  - Similar to `developer`, but this role is intended for applications like microservices, not physical users.

## Tables

- **employees**: Contains information about employees.
  - `id`: Six-digit unique primary key with auto-increment.
  - `name`: Name of the employee.
  - `password`: Employee's password.
  - `role_id`: Foreign key linked to the `roles` table.

- **roles**: Contains information about employee roles.
  - `id`: Unique string identifier.
  - `name`: Name of the role.

- **address**: Contains the address of employees.
  - `id`: Shared primary key, which is the primary key of the `employees` table. Since the address is unique, it's better to have a shared primary key.
  - `area`: Area of the address.
  - `pin_code`: PIN code of the address.

## Cheatsheets

Refer to the [file](./CHEATSHEETS.md).
