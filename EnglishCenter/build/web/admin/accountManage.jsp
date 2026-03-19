<%-- 
    Document   : accountManage
    Created on : Mar 18, 2026, 11:55:50 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Tài khoản Quản trị</title>
    </head>
    <body>
        <h2>Quản lý Nhân sự (Staff & Admin)</h2>
        <a href="AdminDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 2px solid #bf9000; padding: 20px; width: 60%; margin-bottom: 30px; background-color: #fff2cc;">
            <h3 style="color: #bf9000; margin-top: 0;">Tạo tài khoản Nội bộ mới</h3>
            <form action="AccountManage" method="POST">
                <input type="hidden" name="action" value="add">
                <table style="width: 100%;">
                    <tr>
                        <td style="width: 30%;"><strong>Loại Tài Khoản (Role):</strong></td>
                        <td>
                            <select name="roleId" required style="width: 95%; padding: 5px;">
                                <option value="2">Nhân viên / Giáo vụ (Staff)</option>
                                <option value="1">Quản trị viên (Admin)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Username:</strong></td>
                        <td><input type="text" name="username" required style="width: 95%; padding: 5px; margin-top: 8px;" placeholder="VD: staff3"></td>
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
                            <button type="submit" style="background-color: #bf9000; color: white; padding: 8px 15px; border: none; font-weight: bold; cursor: pointer;">
                                TẠO TÀI KHOẢN
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>Danh sách Nhân sự vận hành</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #bf9000; color: black;">
                <th>ID</th>
                <th>Role</th>
                <th>Username</th>
                <th>Họ và Tên</th>
                <th>Email</th>
                <th>Trạng Thái</th>
                <th style="text-align: center;">Thao tác</th> </tr>
                    <c:forEach items="${accountList}" var="a">
                <tr>
                    <td>${a.userID}</td>
                    <td style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${a.roleID == 1}"><span style="color: #cc0000;">[ADMIN]</span></c:when>
                            <c:when test="${a.roleID == 2}"><span style="color: #2986cc;">[STAFF]</span></c:when>
                        </c:choose>
                    </td>
                    <td>${a.username}</td>
                    <td><strong>${a.fullName}</strong></td>
                    <td>${a.email}</td>
                    <td style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${a.status == 1}"><span style="color: green;">Active</span></c:when>
                            <c:otherwise><span style="color: red;">Inactive</span></c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <c:if test="${a.userID != sessionScope.LOGIN_USER.userID}">
                            <c:choose>
                                <c:when test="${a.status == 1}">
                                    <form action="AccountManage" method="POST" style="margin: 0;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="userId" value="${a.userID}">
                                        <button type="submit" onclick="return confirm('Xác nhận KHÓA tài khoản này? Người này sẽ không thể đăng nhập được nữa!');" 
                                                style="background-color: #cc0000; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                            Khóa TK
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="AccountManage" method="POST" style="margin: 0;">
                                        <input type="hidden" name="action" value="activate">
                                        <input type="hidden" name="userId" value="${a.userID}">
                                        <button type="submit" onclick="return confirm('Xác nhận MỞ KHÓA cho tài khoản này hoạt động trở lại?');" 
                                                style="background-color: #38761d; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                            Mở Khóa
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>