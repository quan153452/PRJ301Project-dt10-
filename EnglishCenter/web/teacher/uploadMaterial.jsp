<%-- 
    Document   : uploadMaterial
    Created on : Mar 18, 2026, 10:06:19 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đăng tài liệu học tập</title>
    </head>
    <body>
        <h2>Đăng tài liệu cho lớp học</h2>
        <a href="TeacherDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: blue; font-weight: bold;">${msg}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/UploadMaterial" method="POST">
            <input type="hidden" name="action" value="add">
            <p>
                <label>Chọn Lớp Học:</label><br>
                <select name="classId" required style="width: 300px; padding: 5px;">
                    <option value="">-- Vui lòng chọn lớp --</option>
                    <c:forEach items="${classes}" var="c">
                        <option value="${c.classID}">${c.className}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>Tên tài liệu (Tiêu đề):</label><br>
                <input type="text" name="title" required style="width: 300px; padding: 5px;" placeholder="Ví dụ: Slide Bài 1 - Introduction">
            </p>

            <p>
                <label>Đường dẫn file (Google Drive / Dropbox link):</label><br>
                <input type="url" name="fileUrl" required style="width: 300px; padding: 5px;" placeholder="https://...">
            </p>

            <button type="submit" style="padding: 8px 15px; background-color: #2986cc; color: white; border: none; cursor: pointer;">TẢI LÊN TÀI LIỆU</button>
        </form>
        <h3 style="margin-top: 30px;">Tài liệu bạn đã tải lên</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 80%; text-align: left;">
            <tr style="background-color: #2986cc; color: white;">
                <th>Tên tài liệu (Title)</th>
                <th>Đường dẫn File</th>
                <th style="text-align: center;">Thao tác</th>
            </tr>
            <c:forEach items="${materialList}" var="m">
                <tr>
                    <td><strong>${m.title}</strong></td>
                    <td><a href="${m.fileUrl}" target="_blank">Xem File</a></td>
                    <td style="text-align: center;">
                        <form action="${pageContext.request.contextPath}/UploadMaterial" method="POST" style="margin: 0;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="materialId" value="${m.materialID}">
                            <button type="submit" onclick="return confirm('Bạn có chắc chắn muốn XÓA tài liệu này?');" 
                                    style="background-color: #cc0000; color: white; padding: 5px 10px; border: none; cursor: pointer; border-radius: 3px;">
                                Xóa
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
