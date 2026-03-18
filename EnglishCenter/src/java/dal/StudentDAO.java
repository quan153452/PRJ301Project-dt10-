/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author quan
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AttendanceDetail;
import model.EnrolledClass;
import model.Material;
import model.ScheduleDetail;

public class StudentDAO extends DBContext {

    public List<EnrolledClass> getEnrolledClassesByStudent(int studentID) {
        List<EnrolledClass> list = new ArrayList<>();

        // Câu lệnh SQL JOIN 4 bảng để lấy thông tin khóa học của sinh viên
        String sql = "SELECT c.ClassID, c.ClassName, co.CourseName, u.FullName AS TeacherName, c.StartDate, c.EndDate, c.Status "
                + "FROM Enrollments e "
                + "JOIN Classes c ON e.ClassID = c.ClassID "
                + "JOIN Courses co ON c.CourseID = co.CourseID "
                + "JOIN Users u ON c.TeacherID = u.UserID "
                + "WHERE e.StudentID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new EnrolledClass(
                        rs.getInt("ClassID"),
                        rs.getString("ClassName"),
                        rs.getString("CourseName"),
                        rs.getString("TeacherName"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ScheduleDetail> getWeeklyTimetable(int studentID, java.sql.Date fromDate, java.sql.Date toDate) {
        List<ScheduleDetail> list = new ArrayList<>();

        // Sử dụng LEFT JOIN với Attendance vì có thể có những buổi học chưa diễn ra (chưa điểm danh)
        String sql = "SELECT c.ClassID, t.SlotDate, t.SlotTime, c.ClassName, r.RoomName, a.IsPresent "
                + "FROM Timetable t "
                + "JOIN Classes c ON t.ClassID = c.ClassID "
                + "JOIN Enrollments e ON c.ClassID = e.ClassID "
                + "JOIN Rooms r ON t.RoomID = r.RoomID "
                + "LEFT JOIN Attendance a ON t.SlotID = a.SlotID AND e.StudentID = a.StudentID "
                + "WHERE e.StudentID = ? AND t.SlotDate BETWEEN ? AND ? "
                + "ORDER BY t.SlotDate, t.SlotTime";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentID);
            ps.setDate(2, fromDate);
            ps.setDate(3, toDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Xử lý trạng thái điểm danh (Nếu null trong DB nghĩa là chưa điểm danh -> gán là -1)
                int isPresent = -1;
                if (rs.getObject("IsPresent") != null) {
                    // Trong SQL Server, bit trả về kiểu boolean, ta ép kiểu sang int
                    isPresent = rs.getBoolean("IsPresent") ? 1 : 0;
                }

                list.add(new ScheduleDetail(
                        rs.getInt("ClassID"),
                        rs.getDate("SlotDate"),
                        rs.getString("SlotTime"),
                        rs.getString("ClassName"),
                        rs.getString("RoomName"),
                        isPresent
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<AttendanceDetail> getClassAttendance(int studentID, int classID) {
        List<AttendanceDetail> list = new ArrayList<>();

        String sql = "SELECT t.SlotID, t.SlotDate, t.SlotTime, t.Topic, a.IsPresent, a.Note "
                + "FROM Timetable t "
                + "LEFT JOIN Attendance a ON t.SlotID = a.SlotID AND a.StudentID = ? "
                + "WHERE t.ClassID = ? "
                + "ORDER BY t.SlotDate, t.SlotTime";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentID); // Tham số thứ 1 trong LEFT JOIN
            ps.setInt(2, classID);   // Tham số thứ 2 trong WHERE
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int isPresent = -1; // Mặc định là -1 (Chưa diễn ra)
                if (rs.getObject("IsPresent") != null) {
                    isPresent = rs.getBoolean("IsPresent") ? 1 : 0;
                }

                list.add(new AttendanceDetail(
                        rs.getInt("SlotID"),
                        rs.getDate("SlotDate"),
                        rs.getString("SlotTime"),
                        rs.getString("Topic"),
                        isPresent,
                        rs.getString("Note")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertFeedback(int studentID, int classID, int rating, String comment) {
        String sql = "INSERT INTO Feedbacks (StudentID, ClassID, Rating, Comment, CreatedAt) "
                + "VALUES (?, ?, ?, ?, GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentID);
            ps.setInt(2, classID);
            ps.setInt(3, rating);
            ps.setString(4, comment);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Material> getMaterialsByClass(int classID) {
        List<Material> list = new ArrayList<>();
        // Sửa lại đúng tên cột trong DB: MaterialID, Title, FileUrl
        String sql = "SELECT MaterialID, Title, FileUrl "
                + "FROM Materials "
                + "WHERE ClassID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Material(
                        rs.getInt("MaterialID"),
                        rs.getString("Title"),
                        rs.getString("FileUrl")
                ));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(StudentDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }
}
