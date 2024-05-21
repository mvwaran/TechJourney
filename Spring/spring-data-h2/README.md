# Spring data with H2 database

This project serves as a playground for Spring Boot with an H2 database. While other Spring Data projects in the `Spring` folder use `MySQL`, this project was created to learn how to configure the H2 database.

## Journey

- Created simple `entity`, `repository` and `controller` class to represent employee management.
- Updated `application.yml` file with necessary configuration.
- This service has no CREATE employee option, it assumes that data already in DB.
- To make data already available, created `data.sql` inside resources folder with INSERT statements.