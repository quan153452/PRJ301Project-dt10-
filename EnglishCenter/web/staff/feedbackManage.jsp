<%-- 
    Document   : feedbackManage
    Created on : Mar 19, 2026, 3:32:15 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Hòm thư Góp ý & Đánh giá</title>
    </head>
    <body>
        <h2>Hòm thư Đánh giá của Học viên (Feedback)</h2>

    <c:choose>
        <c:when test="${sessionScope.LOGIN_USER.roleID == 1}"><a href="${pageContext.request.contextPath}/AdminDashboard">Quay lại Dashboard (Admin)</a></c:when>
        <c:otherwise><a href="${pageContext.request.contextPath}/StaffDashboard">Quay lại Dashboard (Staff)</a></c:otherwise>
    </c:choose>
    <hr>

    <div style="border: 2px solid #2986cc; padding: 15px; background-color: #f4f9fd; margin-bottom: 20px; width: 80%;">
        <p style="margin: 0; color: #2986cc; font-style: italic;">
            * Đây là khu vực lưu trữ phản hồi của Học viên về chất lượng giảng dạy. Cán bộ Giáo vụ vui lòng theo dõi thường xuyên để có biện pháp xử lý và cải thiện chất lượng trung tâm.
        </p>
    </div>

    <table border="1" cellpadding="10" cellspacing="0" style="width: 80%; text-align: left;">
        <tr style="background-color: #2986cc; color: white;">
            <th style="width: 5%;">ID</th>
            <th style="width: 20%;">Học Viên</th>
            <th style="width: 20%;">Lớp Học</th>
            <th style="width: 10%;">Đánh Giá</th>
            <th style="width: 45%;">Nội Dung Phản Hồi (Comment)</th>
        </tr>
        <c:forEach items="${feedbackList}" var="f">
            <tr>
                <td>#${f.feedbackId}</td>
                <td><strong>${f.studentName}</strong></td>
                <td style="color: #38761d; font-weight: bold;">${f.className}</td>
                <td style="text-align: center; font-size: 18px; color: #bf9000;">
                    ${f.rating} ⭐
                </td>
                <td>
                    <div style="background-color: #f9f9f9; padding: 10px; border-radius: 5px; border-left: 4px solid #ccc;">
                        ${f.comment}
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>