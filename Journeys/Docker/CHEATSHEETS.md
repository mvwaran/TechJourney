# Cheatsheets

| Description         | Syntax                                                                            | Example                                                                                                     |
|---------------------|-----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Pull image          | docker pull <image_name>:<image_version>                                          | `docker pull mysql:8.2.0`                                                                                   |
| Create a container  | docker run --name <instance_name> -e <env_key=env_name> -d <image_name>:<version> | `docker run --name MySQLInstance -e MYSQL_ROOT_PASSWORD=root123 -p 3306:3306 -p 33060:33060 -d mysql:8.2.0` |
| List all containers | docker ps -a (-a also lists stopped container)                                    | `docker ps -a`                                                                                              |
| Start a container   | docker start <container_id>                                                       | `docker start 16c1e28cc1cf`                                                                                 |
| Stop a container    | docker stop <container_id>                                                        | `docker stop 16c1e28cc1cf`                                                                                  |
| Remove a container  | docker rm <container_name>                                                        | `docker rm MySQLInstance`                                                                                   |
