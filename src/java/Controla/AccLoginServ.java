/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controla;

import Model.AccLoginModel;
import dao.Accountant;
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
public class AccLoginServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("acc_email");
        String pass = request.getParameter("acc_pass");
        String msg = "";

        Accountant acc = new Accountant();

        int a = acc.loginAcc(email, pass);

        if (a != 0) {
            
            AccLoginModel accModel = new AccLoginModel(email, a);
            HttpSession hs = request.getSession();
            hs.setAttribute("ACCOUNT_DETAILS", accModel);
            hs.setMaxInactiveInterval(-1);
            response.sendRedirect("AccountantPanel.jsp");

        } else {
            //redirect to login
            msg = "<div class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 10px;'>"
                    + "  Error! Wrong credentials entered"
                    + "</div>";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("AccountantLogin.jsp").forward(request, response);
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
