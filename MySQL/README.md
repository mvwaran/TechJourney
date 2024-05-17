# MySQL setup

## Installation

- User Docker Desktop. Refer Docker tutorial.

## Connect to mysql

`mysql -h localhost -P 3306 -u root -p`

- `-p` prompts for password

## Q/A

1. What is Shared primary key ?
   - Each employee has an address tagged to him, which is going to be unique for each employee, so instead of generating primary key for each address and referencing it with new column in employees table, the primary key of employees table can be also kept as primary key to address table using foreign key constraints.
   - Refer address table creation & insertion & selection query
2. 
