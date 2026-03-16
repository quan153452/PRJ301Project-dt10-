<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Account"%>

<%
    Account user = (Account) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String username = user.getUsername();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>English Center Management</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
</head>

<body>

<!-- HEADER -->
<div class="header">
    <h1>English Center Management System</h1>
    <p>Welcome, <b><%= username %></b></p>
</div>

<!-- NAVIGATION MENU -->
<div class="topnav">

    <a href="homepage.jsp">Home</a>

    <a href="StudentController">Students</a>

    <a href="TeacherController">Teachers</a>

    <a href="CourseController">Courses</a>

    <a href="ClassController">Classes</a>

    <a href="ScheduleController">Schedule</a>

    <a href="PaymentController">Payment</a>

    <a href="LogoutController" style="float:right;background:#C62828;">Logout</a>

</div>

<!-- MAIN CONTENT -->
<div style="padding:20px">

    <h2>Dashboard</h2>

    <p>Welcome to the English Center Management System.</p>

    <h3>Quick Management</h3>

    <p>
        <a href="StudentController" class="btnDetails">Student Management</a>

        <a href="TeacherController" class="btnDetails">Teacher Management</a>

        <a href="CourseController" class="btnDetails">Course Management</a>

        <a href="ClassController" class="btnDetails">Class Management</a>
    </p>

    <p>
        <a href="ScheduleController" class="btnDetails">Schedule</a>

        <a href="PaymentController" class="btnDetails">Payment</a>
    </p>

</div>

<!-- FOOTER -->
<div class="footer">
    <p>English Center Management System © 2026</p>
</div>

</body>
</html>