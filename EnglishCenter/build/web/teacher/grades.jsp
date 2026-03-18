<%-- 
    Document   : grades
    Created on : Mar 18, 2026, 10:16:27 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Điểm số</title>
    </head>
    <body>
        <h2>Quản lý Điểm Tổng Kết (Final Grade)</h2>
        <a href="TeacherDashboard">Quay lại Dashboard</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold;">${msg}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/Grade" method="GET" style="margin-bottom: 20px;">
            <label><strong>Chọn Lớp Học:</strong></label>
            <select name="classId" required style="padding: 5px;">
                <option value="">-- Vui lòng chọn lớp --</option>
                <c:forEach items="${classes}" var="c">
                    <option value="${c.classID}" <c:if test="${c.classID == selectedClassId}">selected</c:if>>
                        ${c.className}
                    </option>
                </c:forEach>
            </select>
            <button type="submit" style="padding: 5px 10px;">Xem danh sách</button>
        </form>

        <c:if test="${not empty studentList}">
            <form action="${pageContext.request.contextPath}/Grade" method="POST">
                <input type="hidden" name="classId" value="${selectedClassId}">

                <table border="1" cellpadding="8" cellspacing="0" style="width: 80%; text-align: left;">
                    <tr style="background-color: #38761d; color: white;">
                        <th style="width: 10%;">STT</th>
                        <th style="width: 20%;">Mã SV</th>
                        <th style="width: 40%;">Tên Sinh Viên</th>
                        <th style="width: 30%;">Điểm Tổng Kết (Hệ số 10)</th>
                    </tr>

                    <c:forEach items="${studentList}" var="s" varStatus="loop">
                        <tr>
                            <td style="text-align: center;">${loop.index + 1}</td>
                            <td>${s.studentId}</td>
                            <td><strong>${s.studentName}</strong></td>
                            <td>
                                <input type="hidden" name="studentIds" value="${s.studentId}">

                                <input type="number" name="grade_${s.studentId}" 
                                       value="${s.finalGrade}" 
                                       min="0" max="10" step="0.1" 
                                       style="width: 100px; padding: 5px;" 
                                       placeholder="Nhập điểm...">
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <br>
                <button type="submit" style="padding: 10px 20px; background-color: #38761d; color: white; border: none; font-weight: bold; cursor: pointer;">
                    LƯU ĐIỂM SỐ
                </button>
            </form>
        </c:if>

    </body>
</html>