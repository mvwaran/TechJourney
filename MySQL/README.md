# MySQL

## Problem statement

- Goal is to manage employees for an IT organization.
- Database management is categorized using roles `admin`, `developer`, `analyst` and `app`.
  - `admin` - Has high level access for entire MySQL account, alternative for `root`. This role manages user.
  - `developer` - Has high level access to only `test_organization` database. This role manages tables.
  - `analyst` - Has only read access to only `test_organization` database. This role only access data.
  - `app` - Similar to `developer`, but this app is not for physical users, but for applications like microservices.
- Tables.
  - `employees` - Contains information about employee.
    - `id` - Six digit ID unique primary key with auto incrementer.
    - `first_name`
    - `last_name`
    - `role_id` - linked as foreign key to `roles` table.
  - `roles` - Contains information about roles employees can have.
    - `id` - Unique string.
    - `name` - Name of role.
  - `address` - Contains address of employee.
    - `id` - Shared primary key, this is primary key of `employees` table, as `address` is unique better to have shared primary key.
    - `area`
    - `pin_code`

## Cheatsheets

Refer to the [file](./CHEATSHEETS.md)