<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>English Center Login</title>
    </head>

    <body>

        <h2>English Center Management System</h2>

        <form action="Login" method="post">

            <table>

                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" required></td>
                </tr>

                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>

                <tr>
                    <td>Role:</td>
                    <td>

                        <select name="role">

                            <option value="student">Student</option>
                            <option value="teacher">Teacher</option>
                            <option value="admin">Admin</option>

                        </select>

                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="Login">
                    </td>
                </tr>

            </table>

        </form>

        <p style="color:red">
            ${error}
        </p>
    </body>
</html>