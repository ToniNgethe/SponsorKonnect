/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import dao.Student;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author toni
 */
@MultipartConfig(maxFileSize = 16177215)
public class StudentRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //get parameters
        String email = request.getParameter("stud_email");
        String pass = request.getParameter("stud_pass");
        String confrm = request.getParameter("stud_confirm");

        String msg = "";
        InputStream inputStream = null;

        Part filePart = request.getPart("profile_pic");

        if (filePart != null) {

            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();

            //check if passwords match
            if (!pass.equals(confrm)) {

                msg = "<div class='alert alert-danger' style='margin-top: 10px; margin-bottom: 20px;'> Passwords don't match</div>";
                request.setAttribute("message", msg);
                request.getRequestDispatcher("Register.jsp").forward(request, response);

            } else {

                //check if stud exists
                Student student = new Student();

                if (student.studentExists(email)) {

                    //student exists
                    msg = "<div class='alert alert-danger' style='margin-top: 10px; margin-bottom: 20px;'>Email account already registered</div>";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);

                } else {

                    //insert user
                    
                    if (student.newStudent(email, pass, inputStream)) {

                        //success
                        msg = "<div class='alert alert-success' style='margin-top: 10px; margin-bottom: 20px;'>Registration was a success,Login to continue</div>";
                        request.setAttribute("msg", msg);
                        request.getRequestDispatcher("login.jsp").forward(request, response);

                    } else {

                        //error
                        msg = "<div class='alert alert-danger' style='margin-top: 10px; margin-bottom: 20px;'>Oops! Something went wrong in your registration</div>";
                        request.setAttribute("message", msg);
                        request.getRequestDispatcher("Register.jsp").forward(request, response);
                    }

                }

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
