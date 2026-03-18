<%-- 
    Document   : timetable
    Created on : Mar 18, 2026, 8:42:24 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thời khóa biểu hàng tuần</title>
</head>
<body>

    <div>
        <h2>Thời khóa biểu hàng tuần</h2>
        <a href="StudentDashboard">Quay lại Dashboard</a>
        <hr>
    </div>

    <table border="1" cellspacing="0" cellpadding="10" style="width: 100%; text-align: center;">
        <thead>
            <tr style="background-color: #4a86e8; color: white;">
                <th>SLOT / DAY</th>
                <c:forEach items="${weekDays}" var="day">
                    <th>
                        <fmt:formatDate value="${day}" pattern="E" /> <br>
                        <fmt:formatDate value="${day}" pattern="dd/MM" /> </th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${slots}" var="slot">
                <tr>
                    <td style="font-weight: bold; background-color: #f1f1f1;">
                        ${slot}
                    </td>

                    <c:forEach items="${weekDays}" var="day">
                        <td>
                            <c:set var="found" value="false" />
                            
                            <c:forEach items="${schedules}" var="s">
                                <c:if test="${s.slotDate.toString() == day.toString() && s.slotTime == slot}">
                                    
                                    <span style="color: #2986cc; font-weight: bold;">${s.className}</span><br>
                                    <span>at ${s.roomName}</span><br>
                                    
                                    <c:choose>
                                        <c:when test="${s.isPresent == 1}">
                                            <span style="color: green;">(attended)</span>
                                        </c:when>
                                        <c:when test="${s.isPresent == 0}">
                                            <span style="color: red;">(absent)</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: orange;">(Not yet)</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <br>
                                    
                                    <a style="background-color: #f6b26b; color: white; padding: 2px 5px; text-decoration: none; font-size: 12px; display: inline-block; margin-top: 5px;" 
                                       href="ViewMaterials?classId=${s.classID}">
                                        View Materials
                                    </a>

                                    <c:set var="found" value="true" />
                                </c:if>
                            </c:forEach>

                            <c:if test="${!found}">
                                -
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>