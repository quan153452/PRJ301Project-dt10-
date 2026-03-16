<%-- 
    Document   : payment
    Created on : Mar 17, 2026, 12:00:21 AM
    Author     : deadg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tuition Payment</title>
</head>
<body>
    <h2>Tuition Fees for ${student.hoTen}</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Class</th>
                <th>Amount</th>
                <th>Due Date / Paid Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${paymentList}" var="p">
                <tr>
                    <td>${p.className}</td>
                    <td>${p.amount}</td>
                    <td>${p.paymentDate}</td>
                    <td>${p.status}</td>
                    <td>
                        <c:if test="${p.status == 'Unpaid'}">
                            <form action="PaymentController" method="POST" style="display:inline;">
                                <input type="hidden" name="paymentID" value="${p.paymentID}">
                                <button type="submit">Pay Now</button>
                            </form>
                        </c:if>
                        <c:if test="${p.status == 'Paid'}">
                            <span style="color: green;">Completed</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="student">Back to Dashboard</a>
</body>
</html>