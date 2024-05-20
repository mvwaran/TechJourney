# Spring data JPA with H2 and MySQL support

## Journey

- Created project using spring initializer with MySQL library.
- H2 is ignored, because this project is aligned with [MySQL Journey](../../MySQL/README.md).
- Goal of this project is employee management in an organization.
- **Simple entity**
  - First created `EmployeeEntity` class and `EmployeeRepsitory` DAO with basic details like id, first name and lastname.
  - Created REST API to read all employees in `EmployeeController` and `EmployeeService` which queries for all employees.
- **`@OneToOne` mapping with Shared primary key**
  - Because address it unique for each employee, better to have employee table primary key as primary key to address table, so flow becomes bidirectional.
  - Created an `AddressEntity`, here the primary key of `AddressEntity` is the primary key of `EmployeeEntity`. This is why it is called **Shared primary key**.
  - `@MapsId` is used in `AddressEntity` to specify that the `AddressEntity` primary key will be the same as the `EmployeeEntity` primary key.
  - Similarly `@PrimaryKeyJoinColumn` is used in `EmployeeEntity` to specify that the primary key of the `AddressEntity` is also a foreign key referencing the primary key of the `EmployeeEntity`.
  - `@JoinColumn` will be used in **owning** class which contains foreign key and `mappedBy` will be used in the non-owning class.
  - To test it along with read all employees API, create employee API is also created. In `EmployeeService` save JPA method is used to save employee.
  - While creating `EmployeeEntity` object, `AddressEntity` object can also been set. But note down that if you set `AddressEntity` then `employeeEntity.getAddressEntity().setEmployeeEntity(employeeEntity);` code is mandatory.
  - Because it is bidirectional employee should refer address & address should refer employee.
- **`@OneToOne` mapping with a foreign key**
  - Each employee have a role assigned, here employee depends on role, but role has no relation with employee, so this is unidirectional flow.
  - Created an `RoleEntity` but this class has no `EmployeeEntity` mapping, but the `RoleEntity` mapping is done in `EmployeeEntity` because employees table has role_id column.
  - Because the `EmployeeEntity` table has foreign key, use `@JoinColumn` and reference it to `RoleEntity` class.
  - No need of `mappedBy` here.