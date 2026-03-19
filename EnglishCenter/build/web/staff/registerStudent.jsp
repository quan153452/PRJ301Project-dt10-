<%-- 
    Document   : registerStudent
    Created on : Mar 18, 2026, 10:51:45 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng ký Học viên</title>
    </head>
    <body>
        <h2>Đăng ký Tài khoản Học viên mới</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 1px solid #2986cc; padding: 20px; width: 50%; background-color: #f4f9fd;">
            <h3 style="color: #2986cc; margin-top: 0;">Thông tin Học viên</h3>

            <form action="RegisterStudent" method="POST">
                <input type="hidden" name="action" value="add">
                <table style="width: 100%;">
                    <tr>
                        <td style="width: 30%;"><strong>Tên đăng nhập:</strong><br><small>(Dùng để login)</small></td>
                        <td><input type="text" name="username" required style="width: 95%; padding: 8px;" placeholder="VD: student200"></td>
                    </tr>
                    <tr>
                        <td><strong>Họ và Tên:</strong></td>
                        <td><input type="text" name="fullName" required style="width: 95%; padding: 8px; margin-top: 10px;" placeholder="VD: Nguyễn Văn A"></td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong></td>
                        <td><input type="email" name="email" required style="width: 95%; padding: 8px; margin-top: 10px;" placeholder="VD: anva@gmail.com"></td>
                    </tr>
                    <tr>
                        <td><strong>Số điện thoại:</strong></td>
                        <td><input type="text" name="phone" required style="width: 95%; padding: 8px; margin-top: 10px;" placeholder="VD: 0912345678"></td>
                    </tr>
                    <tr>
                        <td><strong>Địa chỉ:</strong></td>
                        <td><input type="text" name="address" required style="width: 95%; padding: 8px; margin-top: 10px;" placeholder="VD: Cầu Giấy, Hà Nội"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; padding-top: 20px;">
                            <button type="reset" style="padding: 10px; cursor: pointer; margin-right: 10px;">Nhập lại</button>
                            <button type="submit" style="background-color: #2986cc; color: white; padding: 10px 20px; border: none; font-weight: bold; cursor: pointer;">
                                TẠO TÀI KHOẢN
                            </button>
                        </td>
                    </tr>
                </table>
                <p style="color: #666; font-style: italic; font-size: 13px; margin-top: 15px;">* Mật khẩu mặc định sau khi tạo thành công sẽ là: <strong>123</strong></p>
            </form>
        </div>
        <h3 style="margin-top: 40px;">Danh sách Học viên Hệ thống</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #2986cc; color: white;">
                <th>ID</th>
                <th>Username</th>
                <th>Họ và Tên</th>
                <th>Email</th>
                <th>Số Điện Thoại</th>
                <th>Trạng Thái</th>
                <th style="text-align: center;">Thao tác</th>
            </tr>
            <c:forEach items="${studentList}" var="s">
                <tr>
                    <td>${s.userID}</td>
                    <td>${s.username}</td>
                    <td><strong>${s.fullName}</strong></td>
                    <td>${s.email}</td>
                    <td>${s.phone}</td>
                    <td style="font-weight: bold;">
                        <c:choose>
                            <c:when test="${s.status == 1}">
                                <span style="color: green;">Đang học</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red;">Đã nghỉ học</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <c:choose>
                            <c:when test="${s.status == 1}">
                                <form action="${pageContext.request.contextPath}/RegisterStudent" method="POST" style="margin: 0;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="userId" value="${s.userID}">
                                    <button type="submit" onclick="return confirm('Xác nhận BUỘC THÔI HỌC học viên này? Thao tác này KHÔNG THỂ hoàn tác trên hệ thống!');" 
                                            style="background-color: #cc0000; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px; font-weight: bold;">
                                        Đuổi học
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #999; font-style: italic; font-size: 14px;">Hồ sơ đã chốt</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>