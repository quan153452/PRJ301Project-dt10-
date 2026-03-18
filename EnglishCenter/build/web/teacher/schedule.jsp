<%-- 
    Document   : schedule
    Created on : Mar 18, 2026, 9:50:51 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch dạy của Giảng viên</title>
</head>
<body>

    <div>
        <h2>Xin chào Giảng viên: ${LOGIN_USER.fullName}</h2>
        <a href="TeacherDashboard">Quay lại</a>
        <hr>
        <h3>Lịch dạy hàng tuần</h3>
    </div>

    <table border="1" cellspacing="0" cellpadding="10" style="width: 100%; text-align: center;">
        <thead>
            <tr style="background-color: #38761d; color: white;">
                <th>SLOT / DAY</th>
                <c:forEach items="${weekDays}" var="day">
                    <th>
                        <fmt:formatDate value="${day}" pattern="E" /> <br>
                        <fmt:formatDate value="${day}" pattern="dd/MM" />
                    </th>
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
                                    
                                    <a style="background-color: #e69138; color: white; padding: 4px 8px; text-decoration: none; font-size: 13px; display: inline-block; margin-top: 8px; border-radius: 3px;" 
                                       href="TakeAttendance?slotId=${s.slotID}&classId=${s.classID}">
                                        Take Attendance
                                    </a>

                                    <c:set var="found" value="true" />
                                </c:if>
                            </c:forEach>

                            <c:if test="${!found}"> - </c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>