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
import model.StudentAttendance;
import model.User;

/**
 *
 * @author deadg
 */
public class TakeAttendanceController extends HttpServlet {

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
            out.println("<title>Servlet TakeAttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeAttendanceController at " + request.getContextPath() + "</h1>");
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

        // Lấy SlotID và ClassID từ URL khi giáo viên bấm "Take Attendance"
        String slotIdStr = request.getParameter("slotId");
        String classIdStr = request.getParameter("classId");

        if (slotIdStr != null && classIdStr != null) {
            int slotId = Integer.parseInt(slotIdStr);
            int classId = Integer.parseInt(classIdStr);

            TeacherDAO dao = new TeacherDAO();
            List<StudentAttendance> studentList = dao.getStudentsForAttendance(classId, slotId);

            // Đẩy dữ liệu ra JSP
            request.setAttribute("studentList", studentList);
            request.setAttribute("slotId", slotId);
            request.setAttribute("classId", classId);

            request.getRequestDispatcher("/teacher/attendance.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/TeacherSchedule");
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

        // 1. Lấy SlotID và mảng các StudentID được gửi lên từ Form
        int slotId = Integer.parseInt(request.getParameter("slotId"));
        String[] studentIds = request.getParameterValues("studentIds");

        TeacherDAO dao = new TeacherDAO();

        // 2. Duyệt qua từng sinh viên để lấy trạng thái và ghi chú
        if (studentIds != null) {
            for (String sidStr : studentIds) {
                int studentId = Integer.parseInt(sidStr);

                // Lấy value của radio button tương ứng với sinh viên này (name = status_123)
                String statusStr = request.getParameter("status_" + studentId);
                // Lấy ghi chú tương ứng (name = note_123)
                String note = request.getParameter("note_" + studentId);

                // Nếu giáo viên có chọn Present(1) hoặc Absent(0) thì mới lưu
                if (statusStr != null && !statusStr.isEmpty()) {
                    int isPresent = Integer.parseInt(statusStr);
                    dao.saveAttendance(slotId, studentId, isPresent, note);
                }
            }
        }

        // 3. Lưu thông báo thành công và chuyển hướng lại trang điểm danh để xem kết quả
        request.setAttribute("msg", "Điểm danh thành công!");
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
