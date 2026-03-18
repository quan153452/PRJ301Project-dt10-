/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author deadg
 */
public class CourseManageController extends HttpServlet {

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
            out.println("<title>Servlet CourseManageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseManageController at " + request.getContextPath() + "</h1>");
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
        if (loginUser == null || loginUser.getRoleID() != 1) { // Chỉ Admin mới được vào
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        AdminDAO dao = new AdminDAO();
        request.setAttribute("courseList", dao.getAllCoursesFull());

        request.getRequestDispatcher("/admin/courseManage.jsp").forward(request, response);
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
        AdminDAO dao = new AdminDAO();

        try {
            if ("add".equals(action)) {
                // Thêm khóa học mới
                String name = request.getParameter("courseName");
                String desc = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                String duration = request.getParameter("duration");

                if (dao.insertCourse(name, desc, price, duration)) {
                    request.setAttribute("msg", "Thêm khóa học mới thành công!");
                } else {
                    request.setAttribute("error", "Lỗi: Không thể thêm khóa học.");
                }

            } else if ("update".equals(action)) {
                // Cập nhật khóa học hiện tại
                int id = Integer.parseInt(request.getParameter("courseId"));
                String desc = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                String duration = request.getParameter("duration");

                if (dao.updateCourse(id, desc, price, duration)) {
                    request.setAttribute("msg", "Cập nhật khóa học thành công!");
                } else {
                    request.setAttribute("error", "Lỗi: Cập nhật thất bại.");
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi định dạng dữ liệu đầu vào!");
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
