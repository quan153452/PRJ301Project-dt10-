<%-- 
    Document   : attendance
    Created on : Mar 18, 2026, 8:55:09 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Báo cáo Điểm danh</title>
    </head>
    <body>

        <div>
            <h2>Chi tiết Điểm danh Môn học</h2>
            <a href="StudentDashboard">Quay lại Dashboard</a>
            <hr>
        </div>

        <div style="margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; background-color: #f9f9f9;">
            <h3>Thống kê tổng quan</h3>
            <ul>
                <li><strong>Tổng số buổi học:</strong> ${totalSlots}</li>
                <li><strong>Số buổi đã vắng:</strong> ${absentCount}</li>
                <li>
                    <strong>Tỉ lệ vắng mặt:</strong> 
                    <span style="color: ${absentPercentage >= 20 ? 'red' : 'green'}; font-weight: bold;">
                        <fmt:formatNumber value="${absentPercentage}" maxFractionDigits="1" /> %
                    </span>
                <c:if test="${absentPercentage >= 20}">
                    <span style="color: red;">(Cảnh báo: Cấm thi!)</span>
                </c:if>
                </li>
            </ul>
        </div>

        <table border="1" cellspacing="0" cellpadding="8" style="width: 100%; text-align: left;">
            <tr style="background-color: #4a86e8; color: white;">
                <th>Buổi (Slot)</th>
                <th>Ngày học (Date)</th>
                <th>Ca học (Time)</th>
                <th>Chủ đề (Topic)</th>
                <th>Trạng thái (Status)</th>
                <th>Ghi chú (Note)</th>
            </tr>

            <c:forEach items="${attendanceList}" var="a" varStatus="loop">
                <tr>
                    <td style="text-align: center;">${loop.index + 1}</td>
                    <td><fmt:formatDate value="${a.slotDate}" pattern="dd/MM/yyyy" /></td>
                <td>${a.slotTime}</td>
                <td>${a.topic}</td>

                <td style="font-weight: bold; text-align: center;">
                <c:choose>
                    <c:when test="${a.isPresent == 1}">
                        <span style="color: green;">Present</span>
                    </c:when>
                    <c:when test="${a.isPresent == 0}">
                        <span style="color: red;">Absent</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: gray;">Future</span>
                    </c:otherwise>
                </c:choose>
                </td>

                <td>${empty a.note ? '' : a.note}</td>
                </tr>
            </c:forEach>

            <c:if test="${empty attendanceList}">
                <tr>
                    <td colspan="6" style="text-align: center;">Không có dữ liệu điểm danh cho lớp học này.</td>
                </tr>
            </c:if>
        </table>

    </body>
</html>