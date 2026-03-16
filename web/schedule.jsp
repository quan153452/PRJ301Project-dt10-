<%-- 
    Document   : schedule
    Created on : Mar 16, 2026, 11:26:47 PM
    Author     : quan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Learning Schedule</title>
    </head>
    <body>

        <h2>Your Learning Schedule</h2>

        <c:choose>
            <c:when test="${not empty scheduleList}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Class Name</th>
                            <th>Day of Week</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Room</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${scheduleList}" var="s">
                            <tr>
                                <td>${s.className}</td>
                                <td>${s.dayOfWeek}</td>
                                <td>${s.startTime}</td>
                                <td>${s.endTime}</td>
                                <td>${s.room}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>You are not currently enrolled in any classes with an active schedule.</p>
            </c:otherwise>
        </c:choose>

        <br/>
        <a href="studentHome.jsp">Back to Dashboard</a>

    </body>
</html>