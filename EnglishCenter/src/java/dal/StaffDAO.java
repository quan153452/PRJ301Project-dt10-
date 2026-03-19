/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author quan
 */
import model.TuitionFee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDAO extends DBContext {

    // Lấy danh sách đăng ký học để thu tiền (Sắp xếp người chưa nộp lên đầu)
    public List<TuitionFee> getAllTuitions() {
        List<TuitionFee> list = new ArrayList<>();
        String sql = "SELECT e.EnrollID, u.FullName AS StudentName, co.CourseName, c.ClassName, co.Price, e.PaymentStatus "
                + "FROM Enrollments e "
                + "JOIN Users u ON e.StudentID = u.UserID "
                + "JOIN Classes c ON e.ClassID = c.ClassID "
                + "JOIN Courses co ON c.CourseID = co.CourseID "
                + "ORDER BY e.PaymentStatus ASC, c.StartDate DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TuitionFee(
                        rs.getInt("EnrollID"),
                        rs.getString("StudentName"),
                        rs.getString("CourseName"),
                        rs.getString("ClassName"),
                        rs.getDouble("Price"),
                        rs.getBoolean("PaymentStatus")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Cập nhật trạng thái đã thu tiền
    public boolean markAsPaid(int enrollId) {
        String sql = "UPDATE Enrollments SET PaymentStatus = 1 WHERE EnrollID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, enrollId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // 1. Lấy danh sách các Khóa học
    public List<model.Course> getAllCourses() {
        List<model.Course> list = new ArrayList<>();
        String sql = "SELECT CourseID, CourseName FROM Courses";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.Course(rs.getInt("CourseID"), rs.getString("CourseName")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 2. Lấy danh sách Giáo viên (RoleID = 3)
    public List<model.User> getAllTeachers() {
        List<model.User> list = new ArrayList<>();
        String sql = "SELECT UserID, FullName FROM Users WHERE RoleID = 3 AND Status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.User u = new model.User();
                u.setUserID(rs.getInt("UserID"));
                u.setFullName(rs.getString("FullName"));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 3. Lấy danh sách Lớp học hiện có
    public List<model.ClassDetail> getAllClasses() {
        List<model.ClassDetail> list = new ArrayList<>();
        String sql = "SELECT c.ClassID, c.ClassName, co.CourseName, u.FullName AS TeacherName, c.StartDate, c.EndDate, c.Status "
                + "FROM Classes c "
                + "JOIN Courses co ON c.CourseID = co.CourseID "
                + "LEFT JOIN Users u ON c.TeacherID = u.UserID "
                + "ORDER BY c.ClassID DESC"; // Xếp lớp mới tạo lên đầu
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.ClassDetail(
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
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 4. Thêm lớp học mới
    public boolean insertClass(String className, int courseId, int teacherId, java.sql.Date startDate, java.sql.Date endDate, String status) {
        String sql = "INSERT INTO Classes (ClassName, CourseID, TeacherID, StartDate, EndDate, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, className);
            ps.setInt(2, courseId);
            ps.setInt(3, teacherId);
            ps.setDate(4, startDate);
            ps.setDate(5, endDate);
            ps.setString(6, status);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // 1. Lấy danh sách Học viên (RoleID = 4)
    public List<model.User> getAllStudents() {
        List<model.User> list = new ArrayList<>();
        String sql = "SELECT UserID, FullName, Email FROM Users WHERE RoleID = 4 AND Status = 1 ORDER BY FullName";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.User u = new model.User();
                u.setUserID(rs.getInt("UserID"));
                // Nối tên và Email để nhân viên dễ phân biệt nếu trùng tên
                u.setFullName(rs.getString("FullName") + " (" + rs.getString("Email") + ")");
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 2. Lấy danh sách Lớp học đang mở (Chỉ lấy Upcoming và Ongoing)
    public List<model.ClassDetail> getActiveClasses() {
        List<model.ClassDetail> list = new ArrayList<>();
        String sql = "SELECT ClassID, ClassName FROM Classes WHERE Status IN ('Upcoming', 'Ongoing') ORDER BY ClassID DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.ClassDetail c = new model.ClassDetail();
                c.setClassId(rs.getInt("ClassID"));
                c.setClassName(rs.getString("ClassName"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 3. Xếp học viên vào lớp (Insert Enrollment)
    public boolean insertEnrollment(int studentId, int classId) {
        // PaymentStatus và EnrollDate đã được set Default trong SQL
        String sql = "INSERT INTO Enrollments (StudentID, ClassID) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, classId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            // Nếu insert bị trùng (Vi phạm Unique Constraint), sẽ nhảy vào đây
            System.out.println("Lỗi Insert Enrollment: " + ex.getMessage());
        }
        return false;
    }

    // 4. Lấy danh sách lịch sử xếp lớp (10 lượt gần nhất để view)
    public List<model.EnrollmentDetail> getRecentEnrollments() {
        List<model.EnrollmentDetail> list = new ArrayList<>();
        String sql = "SELECT TOP 10 e.EnrollID, u.FullName, c.ClassName, co.CourseName, e.EnrollDate, e.PaymentStatus "
                + "FROM Enrollments e "
                + "JOIN Users u ON e.StudentID = u.UserID "
                + "JOIN Classes c ON e.ClassID = c.ClassID "
                + "JOIN Courses co ON c.CourseID = co.CourseID "
                + "ORDER BY e.EnrollDate DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.EnrollmentDetail(
                        rs.getInt("EnrollID"),
                        rs.getString("FullName"),
                        rs.getString("ClassName"),
                        rs.getString("CourseName"),
                        rs.getTimestamp("EnrollDate"),
                        rs.getBoolean("PaymentStatus")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // 1. Kiểm tra xem Username hoặc Email đã tồn tại chưa
    public boolean checkUserExists(String username, String email) {
        String sql = "SELECT UserID FROM Users WHERE Username = ? OR Email = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; // Đã tồn tại
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // 2. Thêm mới Học viên (RoleID = 4, Status = 1)
    public boolean insertStudent(String username, String password, String fullName, String email, String phone, String address) {
        String sql = "INSERT INTO Users (Username, Password, FullName, Email, Phone, Address, RoleID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, 4, 1)";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // Lấy danh sách chi tiết Giáo viên (RoleID = 3) để hiển thị lên bảng quản lý
    public List<model.User> getTeacherDetails() {
        List<model.User> list = new ArrayList<>();
        String sql = "SELECT UserID, Username, FullName, Email, Phone, Address, Status "
                + "FROM Users WHERE RoleID = 3 ORDER BY FullName";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.User u = new model.User();
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullName(rs.getString("FullName"));
                u.setEmail(rs.getString("Email"));
                u.setPhone(rs.getString("Phone"));
                u.setAddress(rs.getString("Address"));
                u.setStatus(rs.getInt("Status"));
                list.add(u);
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // Thêm mới Giáo viên (RoleID = 3, Status = 1)
    public boolean insertTeacher(String username, String password, String fullName, String email, String phone, String address) {
        String sql = "INSERT INTO Users (Username, Password, FullName, Email, Phone, Address, RoleID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, 3, 1)";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // 1. Lấy danh sách Phòng học
    public List<model.Room> getAllRooms() {
        List<model.Room> list = new ArrayList<>();
        String sql = "SELECT RoomID, RoomName, Capacity FROM Rooms ORDER BY RoomName";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.Room(rs.getInt("RoomID"), rs.getString("RoomName"), rs.getInt("Capacity")));
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // 2. Thêm buổi học mới (Slot) vào Thời khóa biểu
    public boolean insertTimetableSlot(int classId, int roomId, java.sql.Date slotDate, String slotTime, String topic) {
        String sql = "INSERT INTO Timetable (ClassID, RoomID, SlotDate, SlotTime, Topic) VALUES (?, ?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setInt(2, roomId);
            ps.setDate(3, slotDate);
            ps.setString(4, slotTime);
            ps.setString(5, topic);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // 3. Lấy 15 buổi học được xếp gần nhất để staff dễ nhìn
    public List<model.TimetableSlot> getRecentTimetableSlots() {
        List<model.TimetableSlot> list = new ArrayList<>();
        String sql = "SELECT TOP 15 t.SlotID, c.ClassName, r.RoomName, t.SlotDate, t.SlotTime, t.Topic "
                + "FROM Timetable t "
                + "JOIN Classes c ON t.ClassID = c.ClassID "
                + "JOIN Rooms r ON t.RoomID = r.RoomID "
                + "ORDER BY t.SlotID DESC";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.TimetableSlot(
                        rs.getInt("SlotID"),
                        rs.getString("ClassName"),
                        rs.getString("RoomName"),
                        rs.getDate("SlotDate"),
                        rs.getString("SlotTime"),
                        rs.getString("Topic")
                ));
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // Kiểm tra xem có bị trùng lịch Phòng hoặc lịch Lớp không
    public String checkSlotConflict(int classId, int roomId, java.sql.Date slotDate, String slotTime) {
        try {
            // 1. Kiểm tra xem Phòng học này đã bị lớp khác chiếm dụng chưa
            String sqlRoom = "SELECT SlotID FROM Timetable WHERE RoomID = ? AND SlotDate = ? AND SlotTime = ?";
            java.sql.PreparedStatement psRoom = connection.prepareStatement(sqlRoom);
            psRoom.setInt(1, roomId);
            psRoom.setDate(2, slotDate);
            psRoom.setString(3, slotTime);
            java.sql.ResultSet rsRoom = psRoom.executeQuery();
            if (rsRoom.next()) {
                return "Phòng học này đã được xếp cho một lớp khác vào thời gian này!";
            }

            // 2. Kiểm tra xem Lớp học này đã có lịch ở ca này chưa (tránh 1 lớp học 2 môn cùng lúc)
            String sqlClass = "SELECT SlotID FROM Timetable WHERE ClassID = ? AND SlotDate = ? AND SlotTime = ?";
            java.sql.PreparedStatement psClass = connection.prepareStatement(sqlClass);
            psClass.setInt(1, classId);
            psClass.setDate(2, slotDate);
            psClass.setString(3, slotTime);
            java.sql.ResultSet rsClass = psClass.executeQuery();
            if (rsClass.next()) {
                return "Lớp học này đã có lịch học vào ca này rồi!";
            }

            // Mở rộng thêm: Nếu bạn muốn check cả Giảng viên bị trùng lịch thì có thể JOIN với bảng Classes ở đây
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Nếu an toàn đi qua hết các vòng check, trả về null (Không có lỗi trùng lặp)
        return null;
    }

    // 1. XÓA CỨNG (Hard Delete) cho Buổi học
    public boolean deleteTimetableSlot(int slotId) {
        try {
            // Bước 1: Xóa các bản ghi điểm danh (Attendance) liên quan đến Slot này trước
            String sqlAtt = "DELETE FROM Attendance WHERE SlotID = ?";
            java.sql.PreparedStatement psAtt = connection.prepareStatement(sqlAtt);
            psAtt.setInt(1, slotId);
            psAtt.executeUpdate();

            // Bước 2: Xóa buổi học trong Timetable
            String sqlSlot = "DELETE FROM Timetable WHERE SlotID = ?";
            java.sql.PreparedStatement psSlot = connection.prepareStatement(sqlSlot);
            psSlot.setInt(1, slotId);
            return psSlot.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // 2. XÓA MỀM (Soft Delete) cho User (Giáo viên / Học sinh / Staff)
    public boolean deactivateUser(int userId) {
        // Chuyển Status về 0 thay vì xóa data để giữ lại lịch sử đóng học phí/điểm danh
        String sql = "UPDATE Users SET Status = 0 WHERE UserID = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // MỞ KHÓA (Reactivate) cho User
    public boolean activateUser(int userId) {
        // Chuyển Status từ 0 (Khóa) về lại 1 (Hoạt động)
        String sql = "UPDATE Users SET Status = 1 WHERE UserID = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

// Hủy xếp lớp (Unenroll) an toàn - Chỉ cần truyền EnrollID
    public boolean unenrollStudent(int enrollId) {
        try {
            // Bước 1: Tìm StudentID và ClassID dựa vào EnrollID
            int studentId = 0;
            int classId = 0;
            String sqlFind = "SELECT StudentID, ClassID FROM Enrollments WHERE EnrollID = ?";
            java.sql.PreparedStatement psFind = connection.prepareStatement(sqlFind);
            psFind.setInt(1, enrollId);
            java.sql.ResultSet rs = psFind.executeQuery();

            if (rs.next()) {
                studentId = rs.getInt("StudentID");
                classId = rs.getInt("ClassID");
            } else {
                return false; // Không tìm thấy bản ghi xếp lớp này
            }

            // Bước 2: Xóa toàn bộ điểm danh (Attendance) của học sinh này trong lớp này
            String delAtt = "DELETE FROM Attendance WHERE StudentID = ? AND SlotID IN (SELECT SlotID FROM Timetable WHERE ClassID = ?)";
            java.sql.PreparedStatement psAtt = connection.prepareStatement(delAtt);
            psAtt.setInt(1, studentId);
            psAtt.setInt(2, classId);
            psAtt.executeUpdate();

            // Bước 3: Xóa Feedbacks (nếu có)
            String delFb = "DELETE FROM Feedbacks WHERE StudentID = ? AND ClassID = ?";
            java.sql.PreparedStatement psFb = connection.prepareStatement(delFb);
            psFb.setInt(1, studentId);
            psFb.setInt(2, classId);
            psFb.executeUpdate();

            // Bước 4: Chốt hạ - Xóa bản ghi Enrollment
            String delEnroll = "DELETE FROM Enrollments WHERE EnrollID = ?";
            java.sql.PreparedStatement psEnroll = connection.prepareStatement(delEnroll);
            psEnroll.setInt(1, enrollId);
            return psEnroll.executeUpdate() > 0;

        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    //  Lấy danh sách Học viên để quản lý
    public java.util.List<model.User> getStudentDetails() {
        java.util.List<model.User> list = new java.util.ArrayList<>();
        String sql = "SELECT UserID, Username, FullName, Email, Phone, Address, Status "
                + "FROM Users WHERE RoleID = 4 ORDER BY UserID DESC";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.User u = new model.User();
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullName(rs.getString("FullName"));
                u.setEmail(rs.getString("Email"));
                u.setPhone(rs.getString("Phone"));
                u.setAddress(rs.getString("Address"));
                u.setStatus(rs.getInt("Status")); // 1: Đang học, 0: Đã nghỉ
                list.add(u);
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(StaffDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }
}
