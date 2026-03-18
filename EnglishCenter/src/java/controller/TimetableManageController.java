/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.User;

/**
 *
 * @author deadg
 */
public class TimetableManageController extends HttpServlet {

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
            out.println("<title>Servlet TimetableManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimetableManageController at " + request.getContextPath() + "</h1>");
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
        if (loginUser == null || (loginUser.getRoleID() != 2 && loginUser.getRoleID() != 1)) {
            response.sendRedirect("login.jsp");
            return;
        }

        StaffDAO dao = new StaffDAO();
        // Dùng lại hàm getActiveClasses() đã viết ở phần Enroll
        request.setAttribute("classList", dao.getActiveClasses());
        request.setAttribute("roomList", dao.getAllRooms());
        request.setAttribute("recentSlots", dao.getRecentTimetableSlots());

        request.getRequestDispatcher("/staff/timetableManage.jsp").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");

        int classId = Integer.parseInt(request.getParameter("classId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Date slotDate = Date.valueOf(request.getParameter("slotDate"));
        String slotTime = request.getParameter("slotTime");
        String topic = request.getParameter("topic");

        StaffDAO dao = new StaffDAO();
        boolean success = dao.insertTimetableSlot(classId, roomId, slotDate, slotTime, topic);

        if (success) {
            request.setAttribute("msg", "Đã xếp lịch (Tạo Slot) thành công!");
        } else {
            request.setAttribute("error", "Lỗi: Không thể xếp lịch học.");
        }

        doGet(request, response);
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
