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
import model.StudentAttendance;
import model.TeachingSchedule;

public class TeacherDAO extends DBContext {

    public List<TeachingSchedule> getWeeklySchedule(int teacherID, java.sql.Date fromDate, java.sql.Date toDate) {
        List<TeachingSchedule> list = new ArrayList<>();

        String sql = "SELECT t.SlotID, c.ClassID, c.ClassName, t.SlotDate, t.SlotTime, r.RoomName "
                + "FROM Timetable t "
                + "JOIN Classes c ON t.ClassID = c.ClassID "
                + "JOIN Rooms r ON t.RoomID = r.RoomID "
                + "WHERE c.TeacherID = ? AND t.SlotDate BETWEEN ? AND ? "
                + "ORDER BY t.SlotDate, t.SlotTime";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, teacherID);
            ps.setDate(2, fromDate);
            ps.setDate(3, toDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new TeachingSchedule(
                        rs.getInt("SlotID"),
                        rs.getInt("ClassID"),
                        rs.getString("ClassName"),
                        rs.getDate("SlotDate"),
                        rs.getString("SlotTime"),
                        rs.getString("RoomName")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<StudentAttendance> getStudentsForAttendance(int classId, int slotId) {
        List<StudentAttendance> list = new ArrayList<>();

        String sql = "SELECT u.UserID AS StudentID, u.FullName AS StudentName, "
                + "a.AttendanceID, a.IsPresent, a.Note "
                + "FROM Enrollments e "
                + "JOIN Users u ON e.StudentID = u.UserID "
                + "LEFT JOIN Attendance a ON e.StudentID = a.StudentID AND a.SlotID = ? "
                + "WHERE e.ClassID = ? "
                + "ORDER BY u.FullName"; // Sắp xếp theo tên cho dễ nhìn
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, slotId);
            ps.setInt(2, classId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int isPresent = -1; // -1 nghĩa là chưa điểm danh lần nào
                int attendanceId = 0;

                if (rs.getObject("AttendanceID") != null) {
                    attendanceId = rs.getInt("AttendanceID");
                    isPresent = rs.getBoolean("IsPresent") ? 1 : 0;
                }

                list.add(new StudentAttendance(
                        rs.getInt("StudentID"),
                        rs.getString("StudentName"),
                        attendanceId,
                        isPresent,
                        rs.getString("Note")
                ));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    public void saveAttendance(int slotId, int studentId, int isPresent, String note) {
        // Thử Update trước
        String updateSql = "UPDATE Attendance SET IsPresent = ?, Note = ? WHERE SlotID = ? AND StudentID = ?";
        try {
            PreparedStatement psUpdate = connection.prepareStatement(updateSql);
            psUpdate.setInt(1, isPresent);
            psUpdate.setString(2, note);
            psUpdate.setInt(3, slotId);
            psUpdate.setInt(4, studentId);

            int rowCount = psUpdate.executeUpdate();

            // Nếu update không thành công (chưa có dữ liệu), thì Insert mới
            if (rowCount == 0) {
                String insertSql = "INSERT INTO Attendance (SlotID, StudentID, IsPresent, Note) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = connection.prepareStatement(insertSql);
                psInsert.setInt(1, slotId);
                psInsert.setInt(2, studentId);
                psInsert.setInt(3, isPresent);
                psInsert.setString(4, note);
                psInsert.executeUpdate();
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public List<model.EnrolledClass> getClassesByTeacher(int teacherId) {
        List<model.EnrolledClass> list = new ArrayList<>();
        String sql = "SELECT ClassID, ClassName FROM Classes WHERE TeacherID = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, teacherId);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.EnrolledClass c = new model.EnrolledClass();
                c.setClassID(rs.getInt("ClassID"));
                c.setClassName(rs.getString("ClassName"));
                list.add(c);
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertMaterial(int classId, String title, String fileUrl) {
        String sql = "INSERT INTO Materials (ClassID, Title, FileUrl, CreatedAt) VALUES (?, ?, ?, GETDATE())";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setString(2, title);
            ps.setString(3, fileUrl);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // Lấy danh sách sinh viên và điểm FinalGrade trong 1 lớp
    public java.util.List<model.StudentGrade> getStudentsGrades(int classId) {
        java.util.List<model.StudentGrade> list = new java.util.ArrayList<>();
        String sql = "SELECT u.UserID AS StudentID, u.FullName AS StudentName, e.FinalGrade "
                + "FROM Enrollments e "
                + "JOIN Users u ON e.StudentID = u.UserID "
                + "WHERE e.ClassID = ? "
                + "ORDER BY u.FullName";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            java.sql.ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Double grade = null;
                // Kiểm tra xem cột FinalGrade có bị NULL trong database không
                if (rs.getObject("FinalGrade") != null) {
                    grade = rs.getDouble("FinalGrade");
                }

                list.add(new model.StudentGrade(
                        rs.getInt("StudentID"),
                        rs.getString("StudentName"),
                        grade
                ));
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // Cập nhật điểm FinalGrade cho 1 sinh viên trong lớp
    public void updateStudentGrade(int classId, int studentId, double finalGrade) {
        String sql = "UPDATE Enrollments SET FinalGrade = ? WHERE ClassID = ? AND StudentID = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, finalGrade);
            ps.setInt(2, classId);
            ps.setInt(3, studentId);
            ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(TeacherDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
