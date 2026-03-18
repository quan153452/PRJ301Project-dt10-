/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ScheduleDetail;
import model.User;

/**
 *
 * @author deadg
 */
public class TimetableController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TimetableController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimetableController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 1. Tính toán ngày Thứ 2 và Chủ Nhật của tuần hiện tại
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);

        // 2. Gọi DAO để lấy danh sách các buổi học trong tuần này
        StudentDAO dao = new StudentDAO();
        List<ScheduleDetail> schedules = dao.getWeeklyTimetable(
                loginUser.getUserID(),
                java.sql.Date.valueOf(monday),
                java.sql.Date.valueOf(sunday)
        );

        // 3. Tạo danh sách 7 ngày trong tuần để đẩy ra giao diện làm Cột (Columns)
        List<java.sql.Date> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(java.sql.Date.valueOf(monday.plusDays(i)));
        }

        // 4. Tạo danh sách các Ca học cố định để làm Dòng (Rows)
        // (Khớp đúng với dữ liệu bạn đã insert vào DB: Sáng và Chiều)
        List<String> slots = Arrays.asList("Sáng (08:30 - 10:00)", "Chiều (14:30 - 16:00)");

        // 5. Đẩy dữ liệu sang JSP
        request.setAttribute("weekDays", weekDays);
        request.setAttribute("slots", slots);
        request.setAttribute("schedules", schedules);

        request.getRequestDispatcher("/student/timetable.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
