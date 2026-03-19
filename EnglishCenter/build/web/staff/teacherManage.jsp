<%-- 
    Document   : teacherManage
    Created on : Mar 18, 2026, 11:05:17 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Giảng viên</title>
    </head>
    <body>
        <h2>Danh sách & Quản lý Giảng viên</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 1px solid #38761d; padding: 20px; width: 60%; margin-bottom: 30px; background-color: #f9f9f9;">
            <h3 style="color: #38761d; margin-top: 0;">Tuyển dụng / Thêm hồ sơ Giảng viên mới</h3>
            <form action="TeacherManage" method="POST">
                <input type="hidden" name="action" value="add">
                <table style="width: 100%;">
                    <tr>
                        <td style="width: 30%;"><strong>Username:</strong></td>
                        <td><input type="text" name="username" required style="width: 95%; padding: 5px;" placeholder="VD: teacher99"></td>
                    </tr>
                    <tr>
                        <td><strong>Họ và Tên:</strong></td>
                        <td><input type="text" name="fullName" required style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong></td>
                        <td><input type="email" name="email" required style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Số điện thoại:</strong></td>
                        <td><input type="text" name="phone" required style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Địa chỉ:</strong></td>
                        <td><input type="text" name="address" required style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; padding-top: 15px;">
                            <button type="submit" style="background-color: #38761d; color: white; padding: 8px 15px; border: none; font-weight: bold; cursor: pointer;">
                                THÊM GIẢNG VIÊN
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>Danh sách Giảng viên trung tâm</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #38761d; color: white;">
                <th>ID</th>
                <th>Username</th>
                <th>Họ và Tên</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Trạng Thái</th>
                <th style="text-align: center;">Thao tác</th> </tr>
                    <c:forEach items="${teacherList}" var="t">
                <tr>
                    <td>${t.userID}</td>
                    <td>${t.username}</td>
                    <td><strong>${t.fullName}</strong></td>
                    <td>${t.email}</td>
                    <td>${t.phone}</td>
                    <td style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${t.status == 1}"><span style="color: green;">Đang công tác</span></c:when>
                            <c:otherwise><span style="color: red;">Đã nghỉ việc</span></c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <c:if test="${t.status == 1}">
                            <form action="${pageContext.request.contextPath}/TeacherManage" method="POST" style="margin: 0;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="userId" value="${t.userID}">
                                <button type="submit" onclick="return confirm('Xác nhận ĐÌNH CHỈ / XÓA tài khoản giảng viên này?');" 
                                        style="background-color: #cc0000; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                    Nghỉ việc
                                </button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>