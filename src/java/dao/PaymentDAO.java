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
import java.util.ArrayList;
import java.util.List;
import model.Payment;

public class PaymentDAO extends DBContext {

    public List<Payment> getPaymentsByStudentId(int maHS) {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT p.PaymentID, p.Amount, p.PaymentDate, p.Status, c.ClassName " +
                     "FROM Payments p " +
                     "JOIN Classes c ON p.ClassID = c.ClassID " +
                     "WHERE p.StudentID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, maHS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentID(rs.getInt("PaymentID"));
                p.setAmount(rs.getDouble("Amount"));
                p.setPaymentDate(rs.getDate("PaymentDate"));
                p.setStatus(rs.getString("Status"));
                p.setClassName(rs.getString("ClassName"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updatePaymentStatus(int paymentID) {
        String sql = "UPDATE Payments SET Status = 'Paid', PaymentDate = GETDATE() WHERE PaymentID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, paymentID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
