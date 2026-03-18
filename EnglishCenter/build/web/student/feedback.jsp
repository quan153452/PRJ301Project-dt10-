<%-- 
    Document   : feedback
    Created on : Mar 18, 2026, 9:00:40 PM
    Author     : quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Đánh giá khóa học</title>
    </head>
    <body>
        <h2>Đánh giá chất lượng khóa học</h2>
        <hr>

        <form action="SubmitFeedback" method="POST">
            <input type="hidden" name="classId" value="${classId}">

            <p>
                <strong>Mức độ hài lòng (1-5 sao):</strong><br>
                <input type="radio" name="rating" value="1"> 1 sao (Rất tệ) <br>
                <input type="radio" name="rating" value="2"> 2 sao <br>
                <input type="radio" name="rating" value="3" checked> 3 sao (Bình thường) <br>
                <input type="radio" name="rating" value="4"> 4 sao <br>
                <input type="radio" name="rating" value="5"> 5 sao (Rất tốt)
            </p>

            <p>
                <strong>Ý kiến đóng góp:</strong><br>
                <textarea name="comment" rows="5" cols="50" placeholder="Hãy chia sẻ cảm nhận của bạn về khóa học..."></textarea>
            </p>

            <button type="submit">Gửi đánh giá</button>
            <a href="StudentDashboard">Hủy bỏ</a>
        </form>
    </body>
</html>
