/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TeacherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.EnrolledClass;
import model.User;

/**
 *
 * @author deadg
 */
public class UploadMaterialController extends HttpServlet {

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
            out.println("<title>Servlet UploadMaterialController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadMaterialController at " + request.getContextPath() + "</h1>");
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
        if (loginUser == null || loginUser.getRoleID() != 3) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        TeacherDAO dao = new TeacherDAO();
        request.setAttribute("classes", dao.getClassesByTeacher(loginUser.getUserID()));
        // BƯỚC MỚI: Truyền danh sách tài liệu đã upload
        request.setAttribute("materialList", dao.getMaterialsByTeacher(loginUser.getUserID()));

        request.getRequestDispatcher("/teacher/uploadMaterial.jsp").forward(request, response);
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
        TeacherDAO dao = new TeacherDAO();

        if ("delete".equals(action)) {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            if (dao.deleteMaterial(materialId)) {
                request.setAttribute("msg", "Đã xóa tài liệu thành công!");
            } else {
                request.setAttribute("error", "Lỗi: Không thể xóa tài liệu.");
            }
        } else {
            // Hành động ADD mặc định
            int classId = Integer.parseInt(request.getParameter("classId"));
            String title = request.getParameter("title");
            String fileUrl = request.getParameter("fileUrl");

            if (dao.insertMaterial(classId, title, fileUrl)) {
                request.setAttribute("msg", "Đăng tài liệu thành công!");
            } else {
                request.setAttribute("error", "Đã xảy ra lỗi khi đăng tài liệu.");
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
