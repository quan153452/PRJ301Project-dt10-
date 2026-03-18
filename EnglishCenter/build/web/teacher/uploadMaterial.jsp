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

    </body>
</html>
