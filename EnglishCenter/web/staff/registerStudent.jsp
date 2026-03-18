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

    </body>
</html>