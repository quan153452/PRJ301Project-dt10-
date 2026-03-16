<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="/English_Center/static/css/styles.css" />
    </head>
    <body>
        <div>
            <h2>Login Page</h2>
            <div>
                <%
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if (username == null) username = "";
                    if (password == null) password = "";
                %>
                <form action="LoginController" method="POST">
                    <label>Username:</label>
                    <input type="text" name="username" value="<%= username %>" />
                    <br>
                    <label>Password:</label>
                    <input type="password" name="password" value="<%= password %>" />
                    <br>
                    <button type="submit">Login</button>
                </form>
                <%
                    String error = (String)request.getAttribute("error");
                    if (error == null) error = "";
                %>
                <div><%= error %></div>
            </div>
        </div>
    </body>
</html>