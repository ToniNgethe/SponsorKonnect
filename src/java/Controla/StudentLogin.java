/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.StudentModel;
import dao.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author toni
 */
public class StudentLogin extends HttpServlet {

    private Student student;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("student_email");
        String pass = request.getParameter("student_pass");
        String msg = "";

        student = new Student();

        int a = student.loginStudent(email, pass);

        //if zero, student doesnt exists
        if (a == 0) {

            //redirect to login
            msg = "<div class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 10px;'>"
                    + "  Error! Wrong credentials entered"
                    + "</div>";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            
            //msg to display
            //after creating session, check if stud reg is complete
            Student std = new Student();
            //check personal details first
            if (!std.isParentDetailsExist(String.valueOf(a))) {

                msg = "<div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Continue with your registration</div>";
                StudentModel studentModel = new StudentModel(a, email, msg);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("STUDENT_MODEL", studentModel);
                httpSession.setMaxInactiveInterval(-1);
                response.sendRedirect("StudentPanel.jsp?id=" + a);

            } else if (!std.checkAllParents(String.valueOf(a))) {

                msg = "<div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Continue with Parent registration</div>";

                StudentModel studentModel = new StudentModel(a, email, msg);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("STUDENT_MODEL", studentModel);
                httpSession.setMaxInactiveInterval(-1);
                response.sendRedirect("StudentPanel.jsp?id=" + a);

            } else if (std.totalSiblingsAdded(String.valueOf(a)) == 0) {

                msg = "<div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Continue with sibling registration</div>";

                StudentModel studentModel = new StudentModel(a, email, msg);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("STUDENT_MODEL", studentModel);
                httpSession.setMaxInactiveInterval(-1);
                response.sendRedirect("StudentPanel.jsp?id=" + a);

            } else if (!std.checkAllSkuls(String.valueOf(a))) {

                msg = "<div id='err' class='alert alert-info' role='alert' style='margin-right: 20px; margin-left: 20px; margin-top: 10px;' >Continue with School registration</div>";
                StudentModel studentModel = new StudentModel(a, email, msg);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("STUDENT_MODEL", studentModel);
                httpSession.setMaxInactiveInterval(-1);
                response.sendRedirect("StudentPanel.jsp?id=" + a);

            } else {
                StudentModel studentModel = new StudentModel(a, email, msg);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("STUDENT_MODEL", studentModel);
                httpSession.setMaxInactiveInterval(-1);
                response.sendRedirect("StudentPaAfterReg.jsp?id=" + a);
            }

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
        processRequest(request, response);
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
