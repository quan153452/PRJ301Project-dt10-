<%-- 
    Document   : dashboard
    Created on : Mar 18, 2026, 6:51:53 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Dashboard</title>
    </head>
    <body>
        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold;">${msg}</p>
        </c:if>
        <div>
            <p>Xin chào, 
                <a href="Profile" style="text-decoration: none; color: #2986cc;">
                    <strong>${sessionScope.LOGIN_USER.fullName}</strong>
                </a>
                (Click vào tên để xem Hồ sơ)
            </p>             <a href="Logout">Đăng xuất</a>
        </div>

        <hr>

        <div>
            <h3>Các khóa học của bạn</h3>

            <c:if test="${empty classList}">
                <p>Bạn chưa đăng ký tham gia lớp học nào!</p>
            </c:if>

            <c:forEach items="${classList}" var="c">
                <div style="margin-bottom: 20px; border: 1px solid #000; padding: 10px;"> <h4>${c.courseName}</h4>
                    <ul>
                        <li><strong>Lớp:</strong> ${c.className}</li>
                        <li><strong>Giảng viên:</strong> ${c.teacherName}</li>
                        <li>
                            <strong>Thời gian:</strong> 
                            <fmt:formatDate value="${c.startDate}" pattern="dd/MM/yyyy"/> - 
                            <fmt:formatDate value="${c.endDate}" pattern="dd/MM/yyyy"/>
                        </li>
                        <li><strong>Trạng thái:</strong> ${c.status}</li>
                    </ul>

                    <a href="Timetable">Xem thời khóa biểu</a>
                    <a href="AttendanceReport?classId=${c.classID}">Xem điểm danh</a>
                    <a href="SubmitFeedback?classId=${c.classID}">Đánh giá</a>

                </div>
            </c:forEach>
        </div>

    </body>
</html>
