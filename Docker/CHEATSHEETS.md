# Cheatsheets

<table>
    <thead>
        <tr>
            <th>Description</th>
            <th>Syntax</th>
            <th>Example</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Pull image</td>
            <td>docker pull [[image_name]]:[[version]]</td>
            <td><code>docker pull mysql:8.2.0</code></td>
        </tr>
        <tr>
            <td>Create a container</td>
            <td>docker run --name [[instance_name]] -e [[ENV_KEY=ENV_NAME]] -d [[image_name]]:[[version]]</td>
            <td><code>docker run --name MySQLInstance -e MYSQL_ROOT_PASSWORD=root123 -p 3306:3306 -p 33060:33060 -d mysql:8.2.0</code></td>
        </tr>
        <tr>
            <td>List all containers (-a also lists stopped container)</td>
            <td>docker ps -a</td>
            <td><code>docker ps -a</code></td>
        </tr>
        <tr>
            <td>Start a container</td>
            <td>docker start [[CONTAINER_ID]]</td>
            <td><code>docker start 16c1e28cc1cf</code></td>
        </tr>
        <tr>
            <td>Stop a container</td>
            <td>docker stop [[CONTAINER_ID]]</td>
            <td><code>docker stop 16c1e28cc1cf</code></td>
        </tr>
        <tr>
            <td>Remove a container</td>
            <td>docker stop [[CONTAINER_NAME]]</td>
            <td><code>docker rm MySQLInstance</code></td>
        </tr>
    </tbody>
</table>