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
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO extends DBContext {

    // 1. Tính tổng doanh thu (Chỉ cộng tiền của những người đã nộp: PaymentStatus = 1)
    public double getTotalRevenue() {
        String sql = "SELECT SUM(co.Price) AS TotalRevenue "
                + "FROM Enrollments e "
                + "JOIN Classes c ON e.ClassID = c.ClassID "
                + "JOIN Courses co ON c.CourseID = co.CourseID "
                + "WHERE e.PaymentStatus = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("TotalRevenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // 2. Đếm số lượng người dùng đang hoạt động theo Role
    public int countUsersByRole(int roleId) {
        String sql = "SELECT COUNT(*) AS Total FROM Users WHERE RoleID = ? AND Status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // 1. Lấy toàn bộ danh sách Khóa học
    public java.util.List<model.Course> getAllCoursesFull() {
        java.util.List<model.Course> list = new java.util.ArrayList<>();
        String sql = "SELECT CourseID, CourseName, Description, Price, Duration FROM Courses ORDER BY CourseID DESC";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new model.Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getString("Duration")
                ));
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // 2. Thêm Khóa học mới
    public boolean insertCourse(String courseName, String description, double price, String duration) {
        String sql = "INSERT INTO Courses (CourseName, Description, Price, Duration) VALUES (?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, courseName);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setString(4, duration);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

    // 3. Cập nhật Khóa học (Giá tiền, Thời lượng, Mô tả)
    public boolean updateCourse(int courseId, String description, double price, String duration) {
        String sql = "UPDATE Courses SET Description = ?, Price = ?, Duration = ? WHERE CourseID = ?";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, description);
            ps.setDouble(2, price);
            ps.setString(3, duration);
            ps.setInt(4, courseId);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

// Lấy danh sách tài khoản của Staff (2) và Admin (1)
    public java.util.List<model.User> getStaffAndAdminAccounts() {
        java.util.List<model.User> list = new java.util.ArrayList<>();
        String sql = "SELECT UserID, Username, FullName, Email, Phone, Address, RoleID, Status "
                + "FROM Users WHERE RoleID IN (1, 2) ORDER BY RoleID ASC, FullName ASC";
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
                u.setRoleID(rs.getInt("RoleID"));
                u.setStatus(rs.getInt("Status"));
                list.add(u);
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return list;
    }

    // Thêm tài khoản Staff/Admin mới
    public boolean insertStaffAdminAccount(String username, String password, String fullName, String email, String phone, String address, int roleId) {
        String sql = "INSERT INTO Users (Username, Password, FullName, Email, Phone, Address, RoleID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setInt(7, roleId);
            return ps.executeUpdate() > 0;
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

// Xóa Khóa Học an toàn
    public String deleteCourseSafe(int courseId) {
        try {
            // 1. Kiểm tra xem khóa học này đã có Lớp nào được mở chưa
            String checkSql = "SELECT COUNT(*) AS Total FROM Classes WHERE CourseID = ?";
            java.sql.PreparedStatement psCheck = connection.prepareStatement(checkSql);
            psCheck.setInt(1, courseId);
            java.sql.ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt("Total") > 0) {
                return "Không thể xóa! Khóa học này đang có lớp học hoạt động. Vui lòng cập nhật thay vì xóa.";
            }

            // 2. Nếu an toàn (Chưa có lớp nào), tiến hành xóa cứng
            String deleteSql = "DELETE FROM Courses WHERE CourseID = ?";
            java.sql.PreparedStatement psDelete = connection.prepareStatement(deleteSql);
            psDelete.setInt(1, courseId);
            psDelete.executeUpdate();

            return null; // Không có lỗi

        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(AdminDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return "Lỗi CSDL khi thao tác!";
        }
    }
}
