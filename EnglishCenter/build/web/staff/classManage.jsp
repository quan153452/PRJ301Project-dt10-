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
                <th>Giảng Viên Phụ Trách</th>
                <th>Ngày Bắt Đầu</th>
                <th>Ngày Kết Thúc</th>
                <th>Trạng Thái</th>
            </tr>
            <c:forEach items="${classList}" var="c">
                <tr>
                    <td>${c.classId}</td>
                    <td><strong>${c.className}</strong></td>
                    <td>${c.courseName}</td>
                    <td style="color: #2986cc; font-weight: bold;">${c.teacherName}</td>
                    <td><fmt:formatDate value="${c.startDate}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${c.endDate}" pattern="dd/MM/yyyy"/></td>
                    <td>
                        <span style="padding: 3px 8px; background-color: ${c.status == 'Ongoing' ? '#d9ead3' : '#fce5cd'}; border-radius: 3px;">
                            ${c.status}
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
