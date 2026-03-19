<%-- 
    Document   : dashboard
    Created on : Mar 18, 2026, 9:47:43 PM
    Author     : deadg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Teacher Dashboard</title>
    </head>
    <body>

        <div>
            <h2>Khu vực Giảng Viên</h2>
            <p>Xin chào, 
                <a href="Profile" style="text-decoration: none; color: #2986cc;">
                    <strong>${sessionScope.LOGIN_USER.fullName}</strong>
                </a>
                (Click vào tên để xem Hồ sơ)
            </p>             <a href="Logout">Đăng xuất</a>
        </div>

        <hr>

        <div>
            <h3>Các chức năng quản lý</h3>

            <div style="margin-bottom: 15px; padding: 10px; border: 1px solid #ccc; width: 300px;">
                <h4>1. Lịch Dạy & Điểm Danh</h4>
                <p>Xem thời khóa biểu hàng tuần và thực hiện điểm danh học viên.</p>
                <a href="TeacherSchedule">
                    <button>Truy cập Lịch Dạy</button>
                </a>
            </div>

            <div style="margin-bottom: 15px; padding: 10px; border: 1px solid #ccc; width: 300px;">
                <h4>2. Quản Lý Điểm Số</h4>
                <p>Nhập điểm và đánh giá kết quả học tập của học viên cuối khóa.</p>
                <a href="Grade"> 
                    <button>Nhập Điểm</button>
                </a>
            </div>

            <div style="margin-bottom: 15px; padding: 10px; border: 1px solid #ccc; width: 300px;">
                <h4>3. Tài Liệu Học Tập</h4>
                <p>Tải lên slide bài giảng, bài tập cho các lớp đang phụ trách.</p>
                <a href="UploadMaterial">
                    <button>Quản lý Tài liệu</button>
                </a>
            </div>
        </div>

    </body>
</html>