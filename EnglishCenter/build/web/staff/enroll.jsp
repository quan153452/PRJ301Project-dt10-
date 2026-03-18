<%-- 
    Document   : enroll
    Created on : Mar 18, 2026, 10:41:38 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Xếp lớp Học viên</title>
        <style>
            .search-box {
                width: 100%;
                padding: 8px;
                margin-bottom: 5px;
                box-sizing: border-box;
                border: 1px solid #aaa;
            }
            .select-box {
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <h2>Xếp Học viên vào Lớp học (Enrollment)</h2>
        <a href="StaffDashboard">Quay lại</a>
        <hr>

        <c:if test="${not empty msg}">
            <p style="color: green; font-weight: bold; background-color: #d9ead3; padding: 10px;">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold; background-color: #fce5cd; padding: 10px;">${error}</p>
        </c:if>

        <div style="border: 1px solid #2986cc; padding: 20px; width: 50%; margin-bottom: 30px; background-color: #f4f9fd;">
            <h3 style="color: #2986cc; margin-top: 0;">Form Xếp Lớp</h3>
            <form action="Enroll" method="POST">

                <strong>1. Chọn Học Viên:</strong><br>
                <input type="text" id="searchStudent" class="search-box" placeholder="🔍 Nhập tên hoặc email học viên để tìm nhanh..." onkeyup="filterFunction('searchStudent', 'studentSelect')">

                <select name="studentId" id="studentSelect" class="select-box" required size="5">
                    <c:forEach items="${studentList}" var="s">
                        <option value="${s.userID}">${s.fullName}</option>
                    </c:forEach>
                </select>

                <strong>2. Chọn Lớp Học (Sắp mở/Đang học):</strong><br>
                <input type="text" id="searchClass" class="search-box" placeholder="🔍 Nhập tên lớp để tìm nhanh..." onkeyup="filterFunction('searchClass', 'classSelect')">

                <select name="classId" id="classSelect" class="select-box" required size="4">
                    <c:forEach items="${classList}" var="c">
                        <option value="${c.classId}">${c.className}</option>
                    </c:forEach>
                </select>

                <button type="submit" style="background-color: #2986cc; color: white; padding: 10px 20px; border: none; font-weight: bold; cursor: pointer; margin-top: 10px; width: 100%;">
                    GHI NHẬN (ENROLL)
                </button>
            </form>
        </div>

        <h3>Lịch sử xếp lớp gần đây</h3>
        <table border="1" cellpadding="8" cellspacing="0" style="width: 80%; text-align: left;">
            <tr style="background-color: #2986cc; color: white;">
                <th>Mã Enroll</th>
                <th>Tên Học Viên</th>
                <th>Khóa Học</th>
                <th>Lớp Học</th>
                <th>Thời Gian Xếp Lớp</th>
                <th>Tình Trạng Học Phí</th>
            </tr>
            <c:forEach items="${recentList}" var="r">
                <tr>
                    <td>#${r.enrollId}</td>
                    <td><strong>${r.studentName}</strong></td>
                    <td>${r.courseName}</td>
                    <td style="color: #38761d; font-weight: bold;">${r.className}</td>
                    <td><fmt:formatDate value="${r.enrollDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${r.paymentStatus}">
                                <span style="color: green;">Đã nộp</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: red; font-weight: bold;">Chưa nộp</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <script>
            function filterFunction(inputId, selectId) {
                var input, filter, select, options, i, txtValue;
                input = document.getElementById(inputId);
                filter = input.value.toUpperCase(); // Chuyển từ khóa thành chữ hoa để so sánh
                select = document.getElementById(selectId);
                options = select.getElementsByTagName("option");

                // Duyệt qua từng thẻ option trong dropdown
                for (i = 0; i < options.length; i++) {
                    txtValue = options[i].textContent || options[i].innerText;
                    // Nếu tên học sinh/lớp có chứa từ khóa thì hiện lên, ngược lại ẩn đi
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        options[i].style.display = "";
                    } else {
                        options[i].style.display = "none";
                    }
                }
            }
        </script>
    </body>
</html>