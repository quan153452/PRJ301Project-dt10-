<%-- 
    Document   : timetableManage
    Created on : Mar 18, 2026, 11:14:05 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Xếp Thời Khóa Biểu</title>
    </head>
    <body>
        <h2>Xếp Thời Khóa Biểu (Tạo Buổi Học)</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 1px solid #38761d; padding: 20px; width: 60%; margin-bottom: 30px; background-color: #f9f9f9;">
            <h3 style="color: #38761d; margin-top: 0;">Form Xếp Lịch Mới</h3>

            <form action="${pageContext.request.contextPath}/TimetableManage" method="POST">
                <table style="width: 100%;">
                    <tr>
                        <td style="width: 30%;"><strong>Chọn Lớp Học:</strong></td>
                        <td>
                            <select name="classId" required style="width: 95%; padding: 5px;">
                                <option value="">-- Chọn Lớp Học (Sắp mở/Đang học) --</option>
                                <c:forEach items="${classList}" var="c">
                                    <option value="${c.classId}">${c.className}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Phòng Học:</strong></td>
                        <td>
                            <select name="roomId" required style="width: 95%; padding: 5px; margin-top: 8px;">
                                <option value="">-- Chọn Phòng --</option>
                                <c:forEach items="${roomList}" var="r">
                                    <option value="${r.roomId}">${r.roomName} (Sức chứa: ${r.capacity})</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Ngày Học (Date):</strong></td>
                        <td><input type="date" name="slotDate" required style="padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Ca Học (Time):</strong></td>
                        <td>
                            <select name="slotTime" required style="padding: 5px; margin-top: 8px;">
                                <option value="Sáng (08:30 - 10:00)">Sáng (08:30 - 10:00)</option>
                                <option value="Chiều (14:30 - 16:00)">Chiều (14:30 - 16:00)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Chủ đề (Topic):</strong></td>
                        <td><input type="text" name="topic" required style="width: 95%; padding: 5px; margin-top: 8px;" placeholder="VD: Listening Part 1 - Bổ trợ"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; padding-top: 15px;">
                            <button type="submit" style="background-color: #38761d; color: white; padding: 8px 15px; border: none; font-weight: bold; cursor: pointer;">
                                TẠO BUỔI HỌC (SLOT)
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>Các buổi học được tạo gần đây</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #38761d; color: white;">
                <th>Mã Slot</th>
                <th>Lớp Học</th>
                <th>Phòng Học</th>
                <th>Ngày Học</th>
                <th>Ca Học</th>
                <th>Chủ đề bài giảng</th>
            </tr>
            <c:forEach items="${recentSlots}" var="s">
                <tr>
                    <td>#${s.slotId}</td>
                    <td style="color: #2986cc; font-weight: bold;">${s.className}</td>
                    <td>${s.roomName}</td>
                    <td><fmt:formatDate value="${s.slotDate}" pattern="dd/MM/yyyy"/></td>
                    <td>${s.slotTime}</td>
                    <td>${s.topic}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>