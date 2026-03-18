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
import model.StudentGrade;
import model.User;

/**
 *
 * @author deadg
 */
public class GradeController extends HttpServlet {

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
            out.println("<title>Servlet GradeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GradeController at " + request.getContextPath() + "</h1>");
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

        // 1. Luôn luôn load danh sách các lớp để hiển thị ra dropdown (Select)
        List<EnrolledClass> classes = dao.getClassesByTeacher(loginUser.getUserID());
        request.setAttribute("classes", classes);

        // 2. Nếu giáo viên đã chọn 1 lớp (có classId truyền trên URL), load danh sách học sinh
        String classIdStr = request.getParameter("classId");
        if (classIdStr != null && !classIdStr.isEmpty()) {
            int classId = Integer.parseInt(classIdStr);
            List<StudentGrade> studentList = dao.getStudentsGrades(classId);

            request.setAttribute("selectedClassId", classId);
            request.setAttribute("studentList", studentList);
        }

        request.getRequestDispatcher("/teacher/grades.jsp").forward(request, response);
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

        // Form gửi lên bao gồm classId và mảng studentIds
        String classIdStr = request.getParameter("classId");
        String[] studentIds = request.getParameterValues("studentIds");

        if (classIdStr != null && studentIds != null) {
            int classId = Integer.parseInt(classIdStr);
            TeacherDAO dao = new TeacherDAO();

            // Duyệt qua từng sinh viên để lấy điểm tương ứng do giáo viên nhập
            for (String sidStr : studentIds) {
                int studentId = Integer.parseInt(sidStr);

                // Điểm của sinh viên này sẽ nằm ở ô input có name="grade_123" (với 123 là mã SV)
                String gradeStr = request.getParameter("grade_" + studentId);

                // Nếu giáo viên có nhập điểm (không để trống)
                if (gradeStr != null && !gradeStr.trim().isEmpty()) {
                    double grade = Double.parseDouble(gradeStr);
                    // Cập nhật vào DB
                    dao.updateStudentGrade(classId, studentId, grade);
                }
            }

            // Set thông báo thành công
            request.setAttribute("msg", "Đã lưu điểm thành công!");
        }

        // Gọi lại doGet để load lại trang kèm theo dữ liệu điểm mới nhất
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
