<%-- 
    Document   : classManage
    Created on : Mar 18, 2026, 10:33:33 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Lớp học</title>
    </head>
    <body>
        <h2>Quản lý Lớp học & Phân công Giảng viên</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold;">${msg}</p>
        </c:if>

        <div style="border: 1px solid #38761d; padding: 20px; width: 60%; margin-bottom: 30px; background-color: #f9f9f9;">
            <h3 style="color: #38761d; margin-top: 0;">Tạo lớp học mới</h3>
            <form action="${pageContext.request.contextPath}/ClassManage" method="POST">
                <input type="hidden" name="action" value="add">
                <table style="width: 100%;">
                    <tr>
                        <td><strong>Tên Lớp (Mã lớp):</strong></td>
                        <td><input type="text" name="className" required style="width: 90%; padding: 5px;" placeholder="VD: TOEIC 750 - K45"></td>
                    </tr>
                    <tr>
                        <td><strong>Khóa Học:</strong></td>
                        <td>
                            <select name="courseId" required style="width: 95%; padding: 5px;">
                                <option value="">-- Chọn Khóa học --</option>
                                <c:forEach items="${courseList}" var="co">
                                    <option value="${co.courseId}">${co.courseName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Phân công Giảng viên:</strong></td>
                        <td>
                            <select name="teacherId" required style="width: 95%; padding: 5px;">
                                <option value="">-- Chọn Giảng viên --</option>
                                <c:forEach items="${teacherList}" var="tc">
                                    <option value="${tc.userID}">${tc.fullName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Ngày Khai Giảng:</strong></td>
                        <td><input type="date" name="startDate" required style="padding: 5px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Ngày Kết Thúc (Dự kiến):</strong></td>
                        <td><input type="date" name="endDate" required style="padding: 5px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Trạng Thái:</strong></td>
                        <td>
                            <select name="status" style="padding: 5px;">
                                <option value="Upcoming">Sắp mở (Upcoming)</option>
                                <option value="Ongoing">Đang học (Ongoing)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; padding-top: 15px;">
                            <button type="submit" style="background-color: #38761d; color: white; padding: 8px 15px; border: none; font-weight: bold; cursor: pointer;">
                                TẠO LỚP & PHÂN CÔNG
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>Danh sách Lớp học hiện có</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #38761d; color: white;">
                <th>ID</th>
                <th>Tên Lớp</th>
                <th>Thuộc Khóa Học</th>
                <th>Giảng Viên</th>
                <th>Ngày Bắt Đầu</th>
                <th>Ngày Kết Thúc</th>
                <th>Trạng Thái</th>
                <th style="text-align: center;">Thao tác</th> </tr>
                    <c:forEach items="${classList}" var="c">
                <tr>
                    <td>${c.classId}</td>
                    <td><strong>${c.className}</strong></td>
                    <td>${c.courseName}</td>
                    <td style="color: #2986cc; font-weight: bold;">${c.teacherName}</td>
                    <td><fmt:formatDate value="${c.startDate}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${c.endDate}" pattern="dd/MM/yyyy"/></td>
                    <td style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${c.status == 'Ongoing'}">
                                <span style="color: #f1c232;">Đang học</span>
                            </c:when>
                            <c:when test="${c.status == 'Upcoming'}">
                                <span style="color: #2986cc;">Sắp mở</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green;">Đã kết thúc</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <c:choose>
                            <c:when test="${c.status != 'Completed'}">
                                <form action="${pageContext.request.contextPath}/ClassManage" method="POST" style="margin: 0;">
                                    <input type="hidden" name="action" value="close">
                                    <input type="hidden" name="classId" value="${c.classId}">
                                    <button type="submit" onclick="return confirm('Xác nhận KẾT THÚC lớp học này? Khóa học đã hoàn thành?');" 
                                            style="background-color: #e69138; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                        Kết thúc lớp
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #999; font-style: italic; font-size: 14px;">Đã chốt</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
