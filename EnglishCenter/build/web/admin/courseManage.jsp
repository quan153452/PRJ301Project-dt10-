<%-- 
    Document   : courseManage
    Created on : Mar 18, 2026, 11:47:27 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Khóa Học</title>
    </head>
    <body>
        <h2>Quản lý Dữ liệu Khóa Học</h2>
        <a href="AdminDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 2px solid #cc0000; padding: 20px; width: 60%; margin-bottom: 30px; background-color: #fce5cd;">
            <h3 style="color: #cc0000; margin-top: 0;">Khởi tạo Khóa học mới</h3>
            <form action="CourseManage" method="POST">
                <input type="hidden" name="action" value="add">

                <table style="width: 100%;">
                    <tr>
                        <td style="width: 25%;"><strong>Tên Khóa Học:</strong></td>
                        <td><input type="text" name="courseName" required style="width: 95%; padding: 5px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Học Phí (VNĐ):</strong></td>
                        <td><input type="number" name="price" required min="0" step="1000" style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Thời lượng:</strong></td>
                        <td><input type="text" name="duration" required placeholder="VD: 3 tháng, 30 buổi..." style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td><strong>Mô tả chi tiết:</strong></td>
                        <td><input type="text" name="description" style="width: 95%; padding: 5px; margin-top: 8px;"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right; padding-top: 15px;">
                            <button type="submit" style="background-color: #cc0000; color: white; padding: 8px 15px; border: none; font-weight: bold; cursor: pointer;">
                                TẠO KHÓA HỌC
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <h3>Danh sách Khóa học hệ thống</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: left;">
            <tr style="background-color: #cc0000; color: white;">
                <th style="width: 5%;">ID</th>
                <th style="width: 20%;">Tên Khóa Học</th>
                <th style="width: 25%;">Mô Tả</th>
                <th style="width: 15%;">Thời Lượng</th>
                <th style="width: 20%;">Học Phí (VNĐ)</th>
                <th style="width: 15%; text-align: center;">Thao tác</th>
                <th style="width: 10%; text-align: center;">Xóa</th>
            </tr>

            <c:forEach items="${courseList}" var="co">
                <form action="CourseManage" method="POST" style="margin: 0;">
                    <input type="hidden" name="courseId" value="${co.courseId}">

                    <tr>
                        <td>${co.courseId}</td>
                        <td><strong>${co.courseName}</strong></td>
                        <td>
                            <input type="text" name="description" value="${co.description}" style="width: 90%; padding: 4px;">
                        </td>
                        <td>
                            <input type="text" name="duration" value="${co.duration}" style="width: 80%; padding: 4px;">
                        </td>
                        <td>
                            <input type="number" name="price" 
                                   value="<fmt:formatNumber value='${co.price}' pattern='0' groupingUsed='false'/>" 
                                   min="0" step="1000" style="width: 80%; padding: 4px;">
                        </td>
                        <td style="text-align: center;">
                            <button type="submit" name="action" value="update" style="background-color: #f1c232; color: black; border: 1px solid #bf9000; padding: 5px 10px; font-weight: bold; cursor: pointer; border-radius: 3px;">
                                Lưu thay đổi
                            </button>
                        </td>
                        <td style="text-align: center;">
                            <button type="submit" name="action" value="delete" onclick="return confirm('Bạn có chắc chắn muốn XÓA Khóa học này?');" 
                                    style="background-color: #cc0000; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                Xóa
                            </button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>

    </body>
</html>