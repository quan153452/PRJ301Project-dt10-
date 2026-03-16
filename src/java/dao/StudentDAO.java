/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author quan
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;

public class StudentDAO extends DBContext {

    public Student getStudentByAccountId(int accountId) {
        String sql = "SELECT * FROM Students WHERE AccountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setMaHS(rs.getInt("StudentID"));
                s.setHoTen(rs.getNString("FullName"));
                s.setNgaySinh(rs.getDate("BirthDate"));
                s.setSoDienThoai(rs.getString("Phone"));
                s.setEmail(rs.getString("Email"));
                s.setAccountID(rs.getInt("AccountID"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
