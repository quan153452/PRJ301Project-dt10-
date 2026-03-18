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

    public User checkLogin(String username, String password) {
        // Truy vấn kiểm tra username, password và tài khoản phải đang Active (Status = 1)
        String sql = "SELECT UserID, Username, FullName, RoleID FROM Users WHERE Username = ? AND Password = ? AND Status = 1";

        try {
            // Sử dụng biến 'connection' được thừa kế từ DBContext
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Trả về đối tượng User nếu tìm thấy
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("FullName"),
                        rs.getInt("RoleID")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; // Trả về null nếu sai thông tin hoặc có lỗi
    }

    // Bạn có thể viết thêm các hàm khác ở đây, ví dụ lấy danh sách học sinh:
    // public List<User> getAllStudents() { ... }
}
