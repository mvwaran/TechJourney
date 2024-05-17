# Docker commands

docker run -it mysql -h MySQLInstance -u root -p test123


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
            <td>docker pull mysql:8.2.0</td>
        </tr>
        <tr>
            <td>Start a instance</td>
            <td>docker run --name [[instance_name]] -e [[ENV_KEY=ENV_NAME]] -d [[image_name]]:[[version]]</td>
            <td>docker run --name MySQLInstance -e MYSQL_ROOT_PASSWORD=test123 -p 3306:3306 -p 33060:33060 -d mysql:8.2.0</td>
        </tr>
    </tbody>
</table>