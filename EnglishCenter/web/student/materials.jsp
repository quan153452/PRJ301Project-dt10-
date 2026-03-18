<%-- 
    Document   : materials
    Created on : Mar 18, 2026, 9:18:45 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tài liệu học tập</title>
</head>
<body>
    <h2>Danh sách tài liệu học tập</h2>
    <a href="javascript:history.back()">Quay lại</a>
    <hr>

    <c:if test="${empty materials}">
        <p>Hiện tại chưa có tài liệu nào cho lớp học này.</p>
    </c:if>

    <c:if test="${not empty materials}">
        <table border="1" cellpadding="10" cellspacing="0" style="width: 80%;">
            <tr style="background-color: #eee;">
                <th>Tên tài liệu</th>
                <th>Hành động</th>
            </tr>
            <c:forEach items="${materials}" var="m">
                <tr>
                    <td><strong>${m.title}</strong></td>
                    <td>
                        <a href="${m.fileUrl}" target="_blank" style="color: blue;">Xem / Tải về</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>