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
public class ClassManageController extends HttpServlet {

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
            out.println("<title>Servlet ClassManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassManageController at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        StaffDAO dao = new StaffDAO();

        // Truyền 3 danh sách sang JSP: Khóa học, Giáo viên và Lớp học
        request.setAttribute("courseList", dao.getAllCourses());
        request.setAttribute("teacherList", dao.getAllTeachers());
        request.setAttribute("classList", dao.getAllClasses());

        request.getRequestDispatcher("/staff/classManage.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        StaffDAO dao = new StaffDAO();

        // NẾU LÀ HÀNH ĐỘNG ĐÓNG LỚP
        if ("close".equals(action)) {
            int classId = Integer.parseInt(request.getParameter("classId"));
            if (dao.closeClass(classId)) {
                request.setAttribute("msg", "Đã KẾT THÚC lớp học thành công!");
            } else {
                request.setAttribute("error", "Lỗi: Không thể đóng lớp học này.");
            }
        } // NẾU LÀ HÀNH ĐỘNG TẠO LỚP MỚI (Logic cũ của bạn)
        else {
            String className = request.getParameter("className");
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            java.sql.Date startDate = java.sql.Date.valueOf(request.getParameter("startDate"));
            java.sql.Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));
            String status = request.getParameter("status");

            boolean success = dao.insertClass(className, courseId, teacherId, startDate, endDate, status);

            if (success) {
                request.setAttribute("msg", "Tạo lớp học và phân công thành công!");
            } else {
                request.setAttribute("error", "Lỗi: Không thể tạo lớp học!");
            }
        }

        // Gọi lại doGet để load lại dữ liệu mới nhất
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
