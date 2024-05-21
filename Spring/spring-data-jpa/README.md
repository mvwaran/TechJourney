# Spring data JPA with H2 and MySQL support

## Journey

- Created a project using Spring Initializer with the MySQL library.
- H2 is ignored because this project aligns with the [MySQL Journey](../../MySQL/README.md).
- The goal of this project is employee management in an organization.
- **Simple entity**
  - First, created the `EmployeeEntity` class and `EmployeeRepository` DAO with basic details like id, password, and name. 
  - Created a REST API to read all employees in `EmployeeController` and `EmployeeService` which queries for all employees.
- **`@OneToOne` mapping with Shared primary key**
  - Since each employee has a unique address, it's better to have the employee table's primary key as the primary key for the address table, making the flow bidirectional.
  - Created an `AddressEntity`, where the primary key of `AddressEntity` is the primary key of `EmployeeEntity`. This is known as a **shared primary key**.
  - Used `@MapsId` in `AddressEntity` to specify that its primary key will be the same as the `EmployeeEntity` primary key.
  - Similarly, used `@PrimaryKeyJoinColumn` in `EmployeeEntity` to specify that the primary key of the `AddressEntity` is also a foreign key referencing the primary key of the `EmployeeEntity`.
  - `@JoinColumn` is used in the **owning** class that contains the foreign key, while `mappedBy` is used in the non-owning class.
  - To test this along with the read-all-employees API, a create-employee API is also created. In `EmployeeService`, the save JPA method is used to save an employee.
  - While creating an `EmployeeEntity` object, an `AddressEntity` object can also be set. Note that if you set `AddressEntity`, then `employeeEntity.getAddressEntity().setEmployeeEntity(employeeEntity);` is mandatory.
  - Because it is bidirectional, the employee should refer to the address and the address should refer to the employee.
- **`@OneToOne` mapping with a foreign key**
  - Each employee has a role assigned. Here, the employee depends on the role, but the role has no relation with the employee, making this a unidirectional flow.
  - Created a `RoleEntity`, but this class has no `EmployeeEntity` mapping. The `RoleEntity` mapping is done in `EmployeeEntity` because the employees table has a `role_id` column.
  - Since the `EmployeeEntity` table has a foreign key, use `@JoinColumn` and reference it to the `RoleEntity` class.
  - No need for `mappedBy` here.