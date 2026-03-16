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
import model.Schedule;

public class ScheduleDAO extends DBContext {

    public List<Schedule> getScheduleByStudentId(int studentId) {
        List<Schedule> list = new ArrayList<>();
        // Query joins Schedules with Classes and Enrollments to filter by StudentID
        String sql = "SELECT c.ClassName, s.DayOfWeek, s.StartTime, s.EndTime, s.Room " +
                     "FROM Schedules s " +
                     "JOIN Classes c ON s.ClassID = c.ClassID " +
                     "JOIN Enrollments e ON c.ClassID = e.ClassID " +
                     "WHERE e.StudentID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, studentId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setClassName(rs.getString("ClassName"));
                s.setDayOfWeek(rs.getString("DayOfWeek"));
                s.setStartTime(rs.getTime("StartTime"));
                s.setEndTime(rs.getTime("EndTime"));
                s.setRoom(rs.getString("Room"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
