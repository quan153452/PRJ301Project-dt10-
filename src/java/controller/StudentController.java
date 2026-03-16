/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;
import model.Student;

/**
 *
 * @author quan
 */
@WebServlet(name = "StudentController", urlPatterns = {"/student"})
public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        // 1. Security Check: Is the user logged in and is a student?
        if (user == null || !user.getRole().equals("student")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Data Check: Ensure student details are loaded into session
        if (session.getAttribute("student") == null) {
            StudentDAO sDao = new StudentDAO();
            Student student = sDao.getStudentByAccountId(user.getAccountID());
            session.setAttribute("student", student);
        }

        // 3. Forward to the view
        request.getRequestDispatcher("studentHome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
