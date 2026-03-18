<%-- 
    Document   : dashboard
    Created on : Mar 18, 2026, 11:34:28 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
    </head>
    <body>
        <h2>Khu vực Ban Giám Đốc (Quản Trị Viên)</h2>
        <p>Xin chào Boss: <strong style="color: #cc0000;">${LOGIN_USER.fullName}</strong></p>
        <a href="Logout">Đăng xuất</a>
        <hr>

        <h3>Báo cáo Thống kê Tổng quan</h3>

        <div style="display: flex; gap: 20px;">

            <div style="border: 2px solid #cc0000; padding: 20px; border-radius: 8px; width: 220px; text-align: center; background-color: #fce5cd;">
                <h4 style="margin: 0; color: #cc0000;">TỔNG DOANH THU</h4>
                <h2 style="margin: 10px 0;">
                    <fmt:formatNumber value="${totalRevenue}" pattern="#,### VNĐ"/>
                </h2>
            </div>

            <div style="border: 2px solid #2986cc; padding: 20px; border-radius: 8px; width: 180px; text-align: center; background-color: #cfe2f3;">
                <h4 style="margin: 0; color: #2986cc;">HỌC VIÊN</h4>
                <h2 style="margin: 10px 0;">${totalStudents}</h2>
            </div>

            <div style="border: 2px solid #38761d; padding: 20px; border-radius: 8px; width: 180px; text-align: center; background-color: #d9ead3;">
                <h4 style="margin: 0; color: #38761d;">GIẢNG VIÊN</h4>
                <h2 style="margin: 10px 0;">${totalTeachers}</h2>
            </div>

            <div style="border: 2px solid #f1c232; padding: 20px; border-radius: 8px; width: 180px; text-align: center; background-color: #fff2cc;">
                <h4 style="margin: 0; color: #bf9000;">NHÂN VIÊN (STAFF)</h4>
                <h2 style="margin: 10px 0;">${totalStaff}</h2>
            </div>

        </div>

        <h3 style="margin-top: 40px;">Bảng Điều Khiển Quản Trị</h3>
        <ul>
            <li style="margin-bottom: 10px;">
                <a href="CourseManage" style="font-weight: bold;">1. Quản lý Khóa Học (Thêm/Sửa/Cập nhật học phí)</a>
            </li>
            <li style="margin-bottom: 10px;">
                <a href="AccountManage">2. Quản lý Tài khoản Staff & Admin</a>
            </li>
            <li style="margin-bottom: 10px;">
                <a href="StaffDashboard">3. Truy cập phân hệ Staff (Dành cho Admin kiểm tra chéo)</a>
            </li>
        </ul>

    </body>
</html>
