<%-- 
    Document   : login
    Created on : Mar 18, 2026, 4:52:03 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng nhập hệ thống</title>
    </head>
    <body>
        <h2>Đăng nhập</h2>

        <p>${error}</p> 

        <form action="Login" method="POST">
            <label>Tên đăng nhập:</label><br>
            <input type="text" name="username" required><br><br>

            <label>Mật khẩu:</label><br>
            <input type="password" name="password" required><br><br>

            <button type="submit">Đăng nhập</button>
        </form>
    </body>
</html>
