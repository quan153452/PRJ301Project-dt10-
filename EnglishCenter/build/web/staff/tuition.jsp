<%-- 
    Document   : tuition
    Created on : Mar 18, 2026, 10:24:22 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Học phí</title>
    </head>
    <body>
        <h2>Danh sách Học phí Học viên</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

    <c:if test="${not empty msg}">
        <p style="color: green; font-weight: bold;">${msg}</p>
    </c:if>

    <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
        <tr style="background-color: #f1c232; color: black;">
            <th>STT</th>
            <th>Tên Học Viên</th>
            <th>Khóa Học</th>
            <th>Lớp</th>
            <th>Học Phí</th>
            <th>Trạng Thái</th>
            <th style="text-align: center;">Hành Động</th>
        </tr>

        <c:forEach items="${tuitionList}" var="t" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td><strong>${t.studentName}</strong></td>
                <td>${t.courseName}</td>
                <td>${t.className}</td>

                <td><fmt:formatNumber value="${t.price}" pattern="#,### VNĐ"/></td>

            <td style="font-weight: bold;">
            <c:choose>
                <c:when test="${t.paymentStatus}">
                    <span style="color: green;">Đã nộp</span>
                </c:when>
                <c:otherwise>
                    <span style="color: red;">Chưa nộp</span>
                </c:otherwise>
            </c:choose>
            </td>

            <td style="text-align: center;">
            <c:if test="${not t.paymentStatus}">
                <form action="${pageContext.request.contextPath}/Tuition" method="POST" style="margin: 0;">
                    <input type="hidden" name="enrollId" value="${t.enrollId}">
                    <button type="submit" onclick="return confirm('Xác nhận đã thu tiền của học viên này?');" 
                            style="background-color: #2986cc; color: white; padding: 5px 10px; border: none; cursor: pointer;">
                        Xác nhận đã thu
                    </button>
                </form>
            </c:if>
            </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
