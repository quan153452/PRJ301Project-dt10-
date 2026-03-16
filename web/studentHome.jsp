<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Dashboard</title>
</head>
<body>
    <header>
        <h1>Student Portal</h1>
        <p>Welcome, <strong>${student.hoTen}</strong> (ID: ${student.maHS})</p>
    </header>

    <hr>

    <nav>
        <a href="ScheduleController">View Schedule</a><br><br>
        <a href="PaymentController">Pay Tuition</a><br><br>
        <a href="Logout">Logout</a>
    </nav>
</body>
</html>