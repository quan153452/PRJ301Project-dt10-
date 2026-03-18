<%-- 
    Document   : attendance
    Created on : Mar 18, 2026, 10:00:17 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Điểm danh lớp học</title>
</head>
<body>
    <h2>Điểm danh sinh viên</h2>
    
    <a href="${pageContext.request.contextPath}/TeacherSchedule">Quay lại lịch dạy</a>
    <hr>

    <c:if test="${not empty msg}">
        <p style="color: green; font-weight: bold;">${msg}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/TakeAttendance" method="POST">
        <input type="hidden" name="slotId" value="${slotId}">
        <input type="hidden" name="classId" value="${classId}">

        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #38761d; color: white;">
                <th>STT</th>
                <th>Mã SV</th>
                <th>Tên Sinh Viên</th>
                <th style="text-align: center;">Điểm danh (Present / Absent)</th>
                <th>Ghi chú</th>
            </tr>

            <c:forEach items="${studentList}" var="s" varStatus="loop">
                <tr>
                    <td style="text-align: center;">${loop.index + 1}</td>
                    <td>${s.studentId}</td>
                    <td><strong>${s.studentName}</strong></td>
                    <td style="text-align: center;">
                        <input type="hidden" name="studentIds" value="${s.studentId}">

                        <label style="color: green; font-weight: bold; margin-right: 15px;">
                            <input type="radio" name="status_${s.studentId}" value="1" 
                                   <c:if test="${s.isPresent == 1}">checked</c:if>> Present
                        </label>
                        
                        <label style="color: red; font-weight: bold;">
                            <input type="radio" name="status_${s.studentId}" value="0" 
                                   <c:if test="${s.isPresent == 0}">checked</c:if>> Absent
                        </label>
                    </td>
                    <td>
                        <input type="text" name="note_${s.studentId}" value="${s.note}" size="30" placeholder="Lý do vắng (nếu có)...">
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>
        <button type="submit" style="padding: 10px 20px; background-color: #38761d; color: white; font-weight: bold; border: none; cursor: pointer;">LƯU ĐIỂM DANH</button>
    </form>

</body>
</html>