<%-- 
    Document   : dashboard
    Created on : Mar 18, 2026, 10:23:47 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Staff Dashboard</title>
    </head>
    <body>
        <h2>Khu vực Nhân Viên / Giáo Vụ</h2>
        <p>Xin chào, 
            <a href="Profile" style="text-decoration: none; color: #2986cc;">
                <strong>${sessionScope.LOGIN_USER.fullName}</strong>
            </a>
            (Click vào tên để xem Hồ sơ)
        </p> 
        <a href="Logout" style="margin-right: 20px;">Đăng xuất</a>

        <c:if test="${LOGIN_USER.roleID == 1}">
            <a href="AdminDashboard" style="color: #cc0000; font-weight: bold;">
                [Quay lại Admin]
            </a>
        </c:if>
        <hr>
        <table style="width: 100%; border-collapse: collapse;">
            <tr>
                <td style="width: 50%; vertical-align: top; padding-right: 20px;">
                    <div style="border: 2px solid #2986cc; padding: 15px; border-radius: 5px;">
                        <h3 style="color: #2986cc; margin-top: 0;">Khối Nghiệp Vụ Tư Vấn</h3>
                        <p><em>(Dành cho Nhân viên Tuyển sinh & Thu ngân)</em></p>
                        <ul>
                            <li style="margin-bottom: 10px;">
                                <a href="Tuition" style="font-weight: bold;">1. Quản lý Thu Học Phí</a>
                            </li>
                            <li style="margin-bottom: 10px; ">
                                <a href="RegisterStudent" style="font-weight: bold;">2. Đăng ký Học viên mới</a>
                            </li>
                            <li style="margin-bottom: 10px;">
                                <a href="Enroll" style="font-weight: bold;">3. Xếp học viên vào lớp (Enroll)</a>
                            </li>
                        </ul>
                    </div>
                </td>

                <td style="width: 50%; vertical-align: top; padding-left: 20px;">
                    <div style="border: 2px solid #38761d; padding: 15px; border-radius: 5px;">
                        <h3 style="color: #38761d; margin-top: 0;">Khối Nghiệp Vụ Giáo Vụ</h3>
                        <p><em>(Dành cho Nhân viên Quản lý Đào tạo)</em></p>
                        <ul>
                            <li style="margin-bottom: 10px;">
                                <a href="ClassManage" style="font-weight: bold;">1. Quản lý Lớp học & Phân công</a> 
                            </li>
                            <li style="margin-bottom: 10px;">
                                <a href="TimetableManage" style="font-weight: bold;">2. Xếp Lịch học (Timetable)</a>
                            </li>
                            <li style="margin-bottom: 10px;">
                                <a href="TeacherManage" style="font-weight: bold;">3. Quản lý Danh sách Giáo viên</a>
                            </li>
                            <li style="margin-bottom: 10px;">
                                <a href="FeedbackManage" style="color: #cc0000; font-weight: bold;">4. Hòm thư Đánh giá (Feedback)</a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
        </table>

    </body>
</html>