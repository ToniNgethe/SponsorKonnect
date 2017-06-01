/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.SocialLoginModel;
import dao.SocialWorker;
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
public class SocialWorkerLoginServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("social_email");
        String pass = request.getParameter("social_pass");
        String msg = "";

        SocialWorker socialWorker = new SocialWorker();

        if (!email.isEmpty() && !pass.isEmpty()) {

            int a = socialWorker.checkSocialLogin(email, pass);
            if (a != 0) {
                
                SocialLoginModel so = new SocialLoginModel(a, email);
                
                HttpSession hs = request.getSession();
                hs.setAttribute("SOCIAL_WORKER", so);
               
                response.sendRedirect("SocialWorkerPanel.jsp");

            } else {
                //redirect to login
                msg = "<div class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 10px;'>"
                        + " Error! wrong credentials"
                        + "</div>";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("SocialWorkerLogin.jsp").forward(request, response);
            }

        } else {
            //redirect to login
            msg = "<div class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 10px;'>"
                    + " Field(s) cannot be empty"
                    + "</div>";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("SocialWorkerLogin.jsp").forward(request, response);
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
