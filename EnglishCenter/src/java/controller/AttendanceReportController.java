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
import java.util.List;
import model.AttendanceDetail;
import model.User;

/**
 *
 * @author deadg
 */
public class AttendanceReportController extends HttpServlet {

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
            out.println("<title>Servlet AttendanceReportController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceReportController at " + request.getContextPath() + "</h1>");
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

        // Lấy ClassID từ URL
        String classIdStr = request.getParameter("classId");
        if (classIdStr == null || classIdStr.isEmpty()) {
            // Nếu không có tham số classId, đuổi về Dashboard
            response.sendRedirect(request.getContextPath() + "/student/dashboard");
            return;
        }

        try {
            int classId = Integer.parseInt(classIdStr);
            int studentId = loginUser.getUserID();

            StudentDAO dao = new StudentDAO();
            List<AttendanceDetail> attendanceList = dao.getClassAttendance(studentId, classId);

            // Tính toán thống kê vắng mặt
            int totalSlots = attendanceList.size();
            int absentCount = 0;

            for (AttendanceDetail ad : attendanceList) {
                if (ad.getIsPresent() == 0) {
                    absentCount++;
                }
            }

            // Tính phần trăm vắng
            double absentPercentage = 0;
            if (totalSlots > 0) {
                absentPercentage = (absentCount * 100.0) / totalSlots;
            }

            // Đẩy dữ liệu ra JSP
            request.setAttribute("attendanceList", attendanceList);
            request.setAttribute("totalSlots", totalSlots);
            request.setAttribute("absentCount", absentCount);
            request.setAttribute("absentPercentage", absentPercentage);

            request.getRequestDispatcher("/student/attendance.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/student/dashboard");
        }
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
