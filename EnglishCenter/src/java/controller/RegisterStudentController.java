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
import model.User;

/**
 *
 * @author deadg
 */
public class RegisterStudentController extends HttpServlet {

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
            out.println("<title>Servlet RegisterStudentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterStudentController at " + request.getContextPath() + "</h1>");
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
        // Lấy danh sách học sinh đẩy sang JSP
        request.setAttribute("studentList", dao.getStudentDetails());

        request.getRequestDispatcher("/staff/registerStudent.jsp").forward(request, response);
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

        // NẾU LÀ HÀNH ĐỘNG ĐUỔI HỌC (XÓA MỀM)
        if ("delete".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            if (dao.deactivateUser(userId)) {
                request.setAttribute("msg", "Đã cho học viên nghỉ học/Thôi học thành công!");
            } else {
                request.setAttribute("error", "Lỗi: Không thể thao tác.");
            }
        } // NẾU LÀ HÀNH ĐỘNG TẠO HỒ SƠ MỚI
        else {
            String username = request.getParameter("username");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String password = "123";

            if (dao.checkUserExists(username, email)) {
                request.setAttribute("error", "Thất bại: Tên đăng nhập hoặc Email này đã có người sử dụng!");
            } else {
                boolean success = dao.insertStudent(username, password, fullName, email, phone, address);
                if (success) {
                    request.setAttribute("msg", "Tạo tài khoản học viên mới thành công! Mật khẩu: 123");
                } else {
                    request.setAttribute("error", "Lỗi hệ thống: Không thể lưu vào Database.");
                }
            }
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
