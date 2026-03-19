/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author deadg
 */
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DBContext {

// Hàm xử lý Đăng nhập
    public model.User checkLogin(String username, String password) {
        // Cập nhật câu SQL: Lấy toàn bộ (*) hoặc liệt kê đủ các cột
        String sql = "SELECT UserID, Username, Password, FullName, Email, Phone, Address, RoleID, Status "
                + "FROM Users WHERE Username = ? AND Password = ? AND Status = 1";
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                model.User u = new model.User();
                // Lấy đủ mọi thông tin bỏ vào Object User
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setFullName(rs.getString("FullName"));
                u.setEmail(rs.getString("Email"));       // Bổ sung dòng này
                u.setPhone(rs.getString("Phone"));       // Bổ sung dòng này
                u.setAddress(rs.getString("Address"));   // Bổ sung dòng này
                u.setRoleID(rs.getInt("RoleID"));
                u.setStatus(rs.getInt("Status"));

                return u; // Trả về đối tượng User "đầy đủ full-option"
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

// Hàm Đổi mật khẩu
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        // Vừa kiểm tra pass cũ có đúng không, vừa update pass mới nếu đúng
        String checkSql = "SELECT UserID FROM Users WHERE UserID = ? AND Password = ?";
        try {
            java.sql.PreparedStatement psCheck = connection.prepareStatement(checkSql);
            psCheck.setInt(1, userId);
            psCheck.setString(2, oldPassword);
            java.sql.ResultSet rs = psCheck.executeQuery();

            if (rs.next()) { // Nếu pass cũ khớp
                String updateSql = "UPDATE Users SET Password = ? WHERE UserID = ?";
                java.sql.PreparedStatement psUpdate = connection.prepareStatement(updateSql);
                psUpdate.setString(1, newPassword);
                psUpdate.setInt(2, userId);
                return psUpdate.executeUpdate() > 0;
            }
        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(UserDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }
}
