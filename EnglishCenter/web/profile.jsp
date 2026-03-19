<%-- 
    Document   : profile
    Created on : Mar 19, 2026, 2:42:46 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Hồ sơ Cá nhân</title>
    </head>
    <body>
        <h2>Thông tin Cá nhân & Đổi Mật Khẩu</h2>

    <c:choose>
        <c:when test="${sessionScope.LOGIN_USER.roleID == 1}"><a href="AdminDashboard">Quay lại Dashboard (Admin)</a></c:when>
        <c:when test="${sessionScope.LOGIN_USER.roleID == 2}"><a href="StaffDashboard">Quay lại Dashboard (Staff)</a></c:when>
        <c:when test="${sessionScope.LOGIN_USER.roleID == 3}"><a href="TeacherDashboard">Quay lại Dashboard (Giảng viên)</a> </c:when>
        <c:when test="${sessionScope.LOGIN_USER.roleID == 4}"><a href="StudentDashboard">Quay lại Dashboard (Học viên)</a> </c:when>
    </c:choose>
    <a href="${pageContext.request.contextPath}/logout" style="margin-left: 20px; color: red;">Đăng xuất</a>
    <hr>

    <c:if test="${not empty msg}">
        <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
    </c:if>

    <table style="width: 100%; border-collapse: collapse;">
        <tr>
            <td style="width: 50%; vertical-align: top; padding-right: 20px;">
                <div style="border: 2px solid #2986cc; padding: 20px; background-color: #f4f9fd;">
                    <h3 style="color: #2986cc; margin-top: 0;">Thông tin Hồ sơ</h3>
                    <p><strong>Mã ID:</strong> #${sessionScope.LOGIN_USER.userID}</p>
                    <p><strong>Username:</strong> ${sessionScope.LOGIN_USER.username}</p>
                    <p><strong>Họ và Tên:</strong> ${sessionScope.LOGIN_USER.fullName}</p>
                    <p><strong>Email:</strong> ${sessionScope.LOGIN_USER.email}</p>
                    <p><strong>Số điện thoại:</strong> ${sessionScope.LOGIN_USER.phone}</p>
                    <p><strong>Địa chỉ:</strong> ${sessionScope.LOGIN_USER.address}</p>
                    <p><strong>Chức vụ:</strong> 
                    <c:choose>
                        <c:when test="${sessionScope.LOGIN_USER.roleID == 1}">Quản trị viên (Admin)</c:when>
                        <c:when test="${sessionScope.LOGIN_USER.roleID == 2}">Nhân viên (Staff)</c:when>
                        <c:when test="${sessionScope.LOGIN_USER.roleID == 3}">Giảng viên (Teacher)</c:when>
                        <c:when test="${sessionScope.LOGIN_USER.roleID == 4}">Học viên (Student)</c:when>
                    </c:choose>
                    </p>
                </div>
            </td>

            <td style="width: 50%; vertical-align: top; padding-left: 20px;">
                <div style="border: 2px solid #cc0000; padding: 20px; background-color: #fce5cd;">
                    <h3 style="color: #cc0000; margin-top: 0;">Đổi Mật Khẩu</h3>
                    <form action="${pageContext.request.contextPath}/Profile" method="POST">
                        <p>
                            <strong>Mật khẩu hiện tại:</strong><br>
                            <input type="password" name="oldPassword" required style="width: 90%; padding: 8px; margin-top: 5px;">
                        </p>
                        <p>
                            <strong>Mật khẩu mới:</strong><br>
                            <input type="password" name="newPassword" required minlength="3" style="width: 90%; padding: 8px; margin-top: 5px;">
                        </p>
                        <p>
                            <strong>Xác nhận mật khẩu mới:</strong><br>
                            <input type="password" name="confirmPassword" required minlength="3" style="width: 90%; padding: 8px; margin-top: 5px;">
                        </p>
                        <button type="submit" style="background-color: #cc0000; color: white; padding: 10px 20px; border: none; font-weight: bold; cursor: pointer; margin-top: 10px;">
                            LƯU MẬT KHẨU
                        </button>
                    </form>
                </div>
            </td>
        </tr>
    </table>

</body>
</html>